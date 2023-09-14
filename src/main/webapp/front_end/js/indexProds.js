const full = location.protocol + '//' + location.host;

function getRanRecommendProds() {
    const feature_products_el = $("#feature_products");
    const new_products_el = $("#new_products");
    let count = 0;

    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "getRandomProdsWithLimit",
            limit: 10,
        })
    })
        .then(resp => resp.json())
        .then(datas => {
            for (let data of datas) {
                count++;

                let img_url = "img/common/Image_not_available.png";
                if (data.prpicsList.length !== 0) {
                    img_url = data.prpicsList[0].opaProdPicture;
                }

                //這邊有幾項記得改成點選可以跳轉該商品
                let productDiv = `
                    <div class="product" data-product-id="${count}" data-category="${data.opaPrcatsNo}" data-brand="BuyGo"
                        data-price="${data.opaProdPrice}">
                        <div class="entry-media" style=" min-height: 220px; overflow: hidden">
                            <div class="hover">
                                <img src="${img_url}" class="entry-url" style="height: 100%">
                                <ul class="icons unstyled">
                                    <li><a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}" class="circle" data-toggle="lightbox"> <i class="iconfont-search"></i>
                                        </a></li>
                                    <li><a href="#" class="circle add-to-cart" onclick="addToCart(${data.opaProdNo});"> <i
                                                class="iconfont-shopping-cart"></i>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="entry-main">
                            <h5 class="entry-title rec-entry-title">
                                <a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}">${data.opaProdName}</a>
                            </h5>
                            <div class="entry-price">
                                <strong class="price" style="color: #fa6f57; font-weight: bold;">$ ${data.opaProdPrice}</strong>
                            </div>
                        </div>
                    </div>
                `;
                if (count <= 5) {
                    feature_products_el.append(productDiv);
                } else {
                    new_products_el.append(productDiv);
                }
            }
        });
}
getRanRecommendProds();

function addToCart(opaProdNo) {
    if (opaProdNo === 0) {
        opaProdNo = prodId;
    }
    checkLoginStatusShowMemberAcct();
    let opaCartId = {
        memberNo: null,
        opaProdNo: opaProdNo
    }

    fetch("/BuyGo/needLoginApi/opa/cart/selectById", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({ opaCartId }),
    })
        .then(resp => {
            if (resp.status === 401) {
                // 客戶未登入，執行頁面重定向到登入頁
                notLoggedInAction();
            } else if (resp.ok) {
                // 請求成功
                return resp.json();
            } else {
                alert("錯誤狀態" + resp.status);
                return;
            }
        })
        .then(data => {
            let opaCartProductsQty = 1;
            if (data !== null) {
                opaCartProductsQty = data.opaCartProductsQty + 1;
            }

            fetch("/BuyGo/needLoginApi/opa/cart/update", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                },
                body: JSON.stringify({
                    opaCartId: opaCartId,
                    opaCartProductsQty: opaCartProductsQty
                }),
            })
                .then(resp => resp.json())
                .then(body => {
                    const { successful } = body;
                    if (successful) {
                        updateCartItem();
                        const Toast = Swal.mixin({
                            toast: true,
                            position: 'top-end',
                            showConfirmButton: false,
                            timer: 2000,
                            timerProgressBar: true,
                            didOpen: (toast) => {
                                toast.addEventListener('mouseenter', Swal.stopTimer)
                                toast.addEventListener('mouseleave', Swal.resumeTimer)
                            }
                        })

                        Toast.fire({
                            icon: 'success',
                            title: '已加入購物車'
                        })
                    }
                });
        });
}

function updateCartItem() {
    fetch("/BuyGo/needLoginApi/opa/cart/selectByMember", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        // body: JSON.stringify({ opaCartId }),
    })
        .then(resp => {
            if (resp.status === 401) {
                // 客戶未登入，不撈取購物車資料
                return;
            } else if (resp.ok) {
                // 請求成功
                return resp.json();
            } else {
                alert("錯誤狀態" + resp.status);
                return;
            }
        })
        .then(datas => {
            if (datas && datas.length > 0) {
                $(".cart-header").addClass("-none");
                let cart_li = "";
                let prodGrandTotal = 0;
                for (let data of datas) {
                    let prpics_url = "../../../../img/common/Image_not_available.png";
                    if (data.prod.prpicsList.length !== 0) {
                        prpics_url = data.prod.prpicsList[0].opaProdPicture;
                    }
                    let prodSubTTL = data.prod.opaProdPrice * data.opaCartProductsQty;
                    prodGrandTotal += prodSubTTL;
                    cart_li += `
                        <li id="li_${data.prod.opaProdNo}">
                            <div class="item clearfix" data-product-id="${data.prod.opaProdNo}"> 
                                <button type="button" class="s-close" aria-hidden="true" onclick="removeCartProd(this)">×</button> 
                                <a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.prod.opaProdNo}" data-toggle="lightbox" class="entry-thumbnail">
                                    <img src="${prpics_url}" alt="${data.prod.opaProdName}">
                                </a>
                                <h5 class="entry-title cart-entry-title"><a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.prod.opaProdNo}">${data.prod.opaProdName}</a></h5>
                                <span class="entry-price s-entry-price">$ <span class="s-price">${data.prod.opaProdPrice}</span> x <span class="s-qty">${data.opaCartProductsQty}</span></span>
                            </div>
                        </li>
                    `;

                }
                $(".s-cart-items").html(cart_li);
                $(".s-cart-total > .total").html("$ " + prodGrandTotal);
            }
        });
}
updateCartItem();

function removeCartProd(btnEl) {
    let opaProdNo = btnEl.closest(".item").getAttribute("data-product-id");

    let opaCartId = {
        memberNo: null,
        opaProdNo: opaProdNo
    };
    fetch("/BuyGo/needLoginApi/opa/cart/remove", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ opaCartId })
    })
        .then(resp => resp.json())
        .then(body => {
            if (body.successful) {
                $("#li_" + opaProdNo).fadeOut(500, function () {
                    $(this).remove();
                    calcuCartGrandTotal();
                });
            }
        });
}

function calcuCartGrandTotal() {
    let prodEls = document.querySelectorAll(".s-entry-price");
    let grandTotal = 0
    for (let prodEl of prodEls) {
        let price = parseInt(prodEl.querySelector(".s-price").textContent);
        let qty = parseInt(prodEl.querySelector(".s-qty").textContent);
        grandTotal += price * qty;
    }
    $(".s-cart-total > .total").html("$ " + grandTotal);
}