sessionStorage.removeItem("store_data");
checkLoginStatusShowMemberAcct();

const full = location.protocol + '//' + location.host;

function loadCartItems() {

    let emptyCart = `
        <tr class="empty-cart">
            <td colspan="5">
                購物車還空蕩蕩的，<a
                    href="../../../guest/opa/prods/prods.html">請點此去購物</a>。
            </td>
        </tr>
    `;

    fetch("/BuyGo/needLoginApi/opa/cart/selectByMember", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        // body: JSON.stringify({ opaCartId }),
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
        .then(datas => {
            if (datas && datas.length > 0) {
                $(".tbl-cart > tbody").html("");
            }else{
                $(".tbl-cart > tbody").html(emptyCart);
            }
            for (let data of datas) {
                let prpics_url = "../../../../img/common/Image_not_available.png";
                if (data.prod.prpicsList.length !== 0) {
                    prpics_url = data.prod.prpicsList[0].opaProdPicture;
                }
                let prodSubTTL = data.prod.opaProdPrice * data.opaCartProductsQty;
                let prod_tr = `
                    <tr id="tr_${data.prod.opaProdNo}">
                        <td>
                            <input type="checkbox" class="prod-check" onclick="prodCheckBtn()">
                            <a class="entry-thumbnail"
                                href="${prpics_url}"
                                data-toggle="lightbox">
                                <img src="${prpics_url}" alt="" />
                            </a>
                            <a id="${data.prod.opaProdNo}" class="cart-entry-title" href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.prod.opaProdNo}">${data.prod.opaProdName}</a>
                        </td>
                        <td><span class="unit-price">$${data.prod.opaProdPrice}</span></td>
                        <td>
                            <div class="qty-btn-group">
                                <button type="button" class="down" onclick="minusQty(this)" onfocus="focusQtyInput(this)" onblur="blurQtyInput(this)"><i
                                        class="iconfont-caret-down inline-middle"></i></button>
                                <input type="text" value="${data.opaCartProductsQty}" onfocus="focusQtyInput(this)" onblur="blurQtyInput(this)"/>
                                <button type="button" class="up" onclick="addQty(this)" onfocus="focusQtyInput(this)" onblur="blurQtyInput(this)"><i
                                        class="iconfont-caret-up inline-middle"></i></button>
                            </div>
                        </td>
                        <td class="hidden-xs"><strong class="text-bold row-total">$${prodSubTTL}</strong></td>
                        <td class="hidden-xs"><button type="button" class="close" aria-hidden="true" onclick="cartProdRemove(${data.prod.opaProdNo})">×</button>
                        </td>
                    </tr>`;

                $(".tbl-cart > tbody").append(prod_tr);
            }
        });
}
loadCartItems();

function addQty(el) {
    let input = el.closest(".qty-btn-group").querySelector("input");
    // input.value = parseInt(input.value) + 1;
    let qty = parseInt(input.value) + 1;

    let price = parseInt(el.closest("tr").querySelector(".unit-price").textContent.substring(1));

    let subTotal = el.closest("tr").querySelector(".row-total");
    // subTotal.textContent = "$" + qty * price;

    let opaProdNo = el.closest("tr").querySelector(".cart-entry-title").id;
    cartProdQtyUpdate(opaProdNo, qty);
    calcCartAmt();
}

function minusQty(el) {
    let input = el.closest(".qty-btn-group").querySelector("input");
    // input.value = parseInt(input.value) - 1;
    let qty = parseInt(input.value) - 1;

    let price = parseInt(el.closest("tr").querySelector(".unit-price").textContent.substring(1));

    let subTotal = el.closest("tr").querySelector(".row-total");
    // subTotal.textContent = "$" + qty * price;

    let opaProdNo = el.closest("tr").querySelector(".cart-entry-title").id;
    if (qty === 0) {
        cartProdRemove(opaProdNo);
    } else {
        cartProdQtyUpdate(opaProdNo, qty);
    }
    calcCartAmt();
}

function cartProdQtyUpdate(opaProdNo, opaCartProductsQty) {
    let opaCartId = {
        memberNo: null,
        opaProdNo: opaProdNo
    };

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
                console.log("更新購物車成功");
                updateCartItem();
            } else {
                console.log("更新購物車失敗");
            }
        });
}

function focusQtyInput(el) {
    el.closest(".qty-btn-group").classList.add("qty-btn-group-focus");
}

function blurQtyInput(el) {
    el.closest(".qty-btn-group").classList.remove("qty-btn-group-focus");
}

$(".prod-check-all").on("click", function () {
    $(".prod-check").prop("checked", $(".prod-check-all").prop("checked"));
    calcCartAmt();
    $("#noCheckoutItemMsg").addClass("-none");
});

function prodCheckBtn() {
    $(".prod-check-all").prop("checked", $(".prod-check:checked").length === $(".prod-check").length);
    calcCartAmt();
    $("#noCheckoutItemMsg").addClass("-none");
}

let usedCoupon_opaMinAmount = 0;
let usedCoupon_opaDiscountAmo = 0;

function calcCartAmt() {
    let subTTL = 0;
    let GrandTTL = 0;
    let check_els = document.querySelectorAll(".prod-check:checked");
    for (let check_el of check_els) {
        let prodSubTTL = check_el.closest("tr").querySelector(".row-total").textContent.substring(1);
        subTTL += parseInt(prodSubTTL);
    }

    if (usedCoupon_opaMinAmount !== 0 && usedCoupon_opaMinAmount > subTTL) {
        $("#selectCouponBtn").text("選擇優惠券");
        $("#selectCouponBtn").css("background-color", "#fa6f57");
        $("#selectedCouponName").text("");
        $(".couponDiscount").addClass("-none");
        usedCoupon_opaMinAmount = 0;
        usedCoupon_opaDiscountAmo = 0;
    }

    GrandTTL = subTTL - usedCoupon_opaDiscountAmo;
    $(".shopcart-total > .cart-subtotal > span.pull-right").text("$" + subTTL);
    $(".shopcart-total > .cart-total > span.pull-right").text("$" + GrandTTL);
}


function cartProdRemove(opaProdNo) {
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
                $("#tr_" + opaProdNo).fadeOut(1000, function () {
                    $(this).remove();
                });
                $("#li_" + opaProdNo).fadeOut(500, function () {
                    $(this).remove();
                    calcuCartGrandTotal();
                });
            }
        });
}

function getMemberCoupon() {
    let cartSubTTL = parseInt($(".shopcart-total > .cart-subtotal > span.pull-right").text().substring(1));
    $(".s-coupons").html("");
    fetch("/BuyGo/needLoginApi/opa/coupon/getMemberCoupon", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
    })
        .then(resp => resp.json())
        .then(datas => {
            let hasCoupon = false;
            if (datas) {
                for (let data of datas) {
                    let opaExpDate = new Date(data.coupon.opaExpDate);
                    if (data.opaCpownerStatus === 1 || opaExpDate < new Date()) {
                        continue;
                    }
                    let couponDisabled = data.coupon.opaMinAmount > cartSubTTL ? "disabled" : "";
                    let couponMsg = data.coupon.opaMinAmount > cartSubTTL ? "" : "-none";
                    hasCoupon = true;
                    const options = {
                        year: 'numeric',    // 年: 使用4位數
                        month: '2-digit',   // 月: 使用2數位
                        day: '2-digit',     // 日: 使用2數位
                        hour12: false,      // 12小時制: 不使用
                        hour: '2-digit',    // 時: 使用2數位
                        minute: '2-digit',  // 分: 使用2數位
                        second: '2-digit'   // 秒: 使用2數位
                    };
                    let coupon_str = `
                        <div class="radioDiv">
                            <input type="radio" name="couponRadio" id="r_${data.coupon.opaCouponNo}" ${couponDisabled}/>
                            <label for="r_${data.coupon.opaCouponNo}" ${couponDisabled}>
                                <span class="radioButtonGraph"></span>
                                <div class="s-coupon">
                                    <div class="s-coupon-intro">
                                        <span class="${couponMsg}" style="color: red; font-size: 10px">*勾選商品未達訂單門檻</span>
                                        <h4>${data.coupon.opaCouponName}</h4>
                                        <ul>
                                            <li>訂單滿 <span class="coupon_opaMinAmount">${data.coupon.opaMinAmount}</span> 元可用</li>
                                            <li>限 ${new Intl.DateTimeFormat('zh-TW', options).format(opaExpDate)} 前使用</li>
                                        </ul>
                                    </div>
                                    <div class="s-coupon-value">
                                        $ <span class="coupon_opaDiscountAmo">${data.coupon.opaDiscountAmo}</span>
                                    </div>
                                </div>
                            </label>
                        </div>
                    `;
                    $(".s-coupons").append(coupon_str);
                }
            }
            if (!hasCoupon) {
                let coupon_str = `
                    <div>
                        <p>您的優惠券匣目前是空的唷</p>
                    </div>
                `;
                $(".s-coupons").append(coupon_str);
            } else {
                let coupon_unuse_str = `
                    <div class="radioDiv" style="text-align: left;">
                        <input type="radio" name="couponRadio" id="r_unuse" style="top: 3px;"/>
                        <label for="r_unuse" style="vertical-align: middle;">
                            <span class="radioButtonGraph"  style="top: 0px;"></span>
                            <span>不使用優惠券</span>
                        </label>
                    </div>
                `;
                $(".s-coupons").append(coupon_unuse_str);
            }
        });
}

function selectCoupon() {
    getMemberCoupon();

    $("#showCouponList").removeClass("-none");
}

function selectCouponClose() {
    $("#showCouponList").addClass("-none");
    let couponRadio_el = document.querySelector("input[type='radio'][name='couponRadio']:checked");
    if (couponRadio_el && couponRadio_el.id !== "r_unuse") {
        let r_couponNo = couponRadio_el.closest(".radioDiv").querySelector("input").id;
        let couponName = couponRadio_el.closest(".radioDiv").querySelector(".s-coupon-intro > h4").textContent;
        usedCoupon_opaDiscountAmo = parseInt(couponRadio_el.closest(".radioDiv").querySelector(".coupon_opaDiscountAmo").textContent);
        usedCoupon_opaMinAmount = parseInt(couponRadio_el.closest(".radioDiv").querySelector(".coupon_opaMinAmount").textContent);
        $("#selectCouponBtn").text("已選擇優惠券");
        $("#selectCouponBtn").css("background-color", "seagreen");
        $("#selectedCouponName").text(couponName);
        $("#selectedCouponNo").text(r_couponNo.substring(2));
        $(".couponDiscount").removeClass("-none");
        let cartSubTTL = parseInt($(".shopcart-total > .cart-subtotal > span.pull-right").text().substring(1));
        let cartGrandTTL = cartSubTTL - usedCoupon_opaDiscountAmo;
        $(".shopcart-total > .cart-total > span.pull-right").text("$" + cartGrandTTL);
        $(".couponDisAmt").text(usedCoupon_opaDiscountAmo);
    } else {
        $("#selectCouponBtn").text("選擇優惠券");
        $("#selectCouponBtn").css("background-color", "#fa6f57");
        $("#selectedCouponName").text("");
        $("#selectedCouponNo").text()
        $(".couponDiscount").addClass("-none");
        usedCoupon_opaMinAmount = 0;
        usedCoupon_opaDiscountAmo = 0;
    }
}

function checkoutClick() {
    let cartSubTTL = parseInt($(".shopcart-total > .cart-subtotal > span.pull-right").text().substring(1));
    let cartGrandTTL = parseInt($(".shopcart-total > .cart-total > span.pull-right").text().substring(1));
    if (cartSubTTL === 0) {
        $("#noCheckoutItemMsg").removeClass("-none");
        return;
    }

    let couponNo = $("#selectedCouponNo").text();
    let prods = [];
    let check_els = document.querySelectorAll(".prod-check:checked");
    for (let check_el of check_els) {
        let prodNo = check_el.closest("tr").id.substring(3);
        let prodQty = check_el.closest("tr").querySelector(".qty-btn-group > input").value;
        let prod = {
            opaProdNo: prodNo,
            opaCartProdQty: prodQty
        }
        prods.push(prod);
    }
    let cartData = {
        prods: prods,
        opaCouponNo: couponNo,
        opaDiscountAmo: usedCoupon_opaDiscountAmo,
        cartSubTTL: cartSubTTL,
        cartGrandTTL: cartGrandTTL
    }
    sessionStorage.setItem("cart_data", JSON.stringify(cartData));
    window.location = full + "/BuyGo/front_end/pages/member/opa/checkout/checkout.html";
}

function getRanRecommendProds() {
    const feature_products_el = $("#feature_products");
    let count = 0;

    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "getRandomProdsWithLimit",
            limit: 5,
        })
    })
        .then(resp => resp.json())
        .then(datas => {
            for (let data of datas) {
                count++;

                let img_url = "../../../../img/common/Image_not_available.png";
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
                                    <li><a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}" class="circle"> <i class="iconfont-search"></i>
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
                feature_products_el.append(productDiv);
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
                    let prpics_url = "img/common/Image_not_available.png";
                    if (data.prod.prpicsList.length !== 0) {
                        prpics_url = data.prod.prpicsList[0].opaProdPicture;
                    }
                    let prodSubTTL = data.prod.opaProdPrice * data.opaCartProductsQty;
                    prodGrandTotal += prodSubTTL;
                    cart_li += `
                        <li id="li_${data.prod.opaProdNo}">
                            <div class="item clearfix" data-product-id="${data.prod.opaProdNo}">
                                <button type="button" class="s-close" aria-hidden="true" onclick="cartProdRemove(${data.prod.opaProdNo})">×</button>
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

                loadCartItems(); //重新載入購物車品項
            }
        });
}
updateCartItem();


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