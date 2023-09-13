// Image Slider js Start (圖片滑動顯示)
var index = 0;
var slides, dots;

function prevSlide(n) {
    index += n;
    var slides = document.querySelectorAll(".slide2");
    console.log("prevSlide is called");
    changeSlide(slides);
}

function nextSlide(n) {
    index += n;
    var slides = document.querySelectorAll(".slide2");
    changeSlide(slides);
}

function changeSlide(slides) {

    var dots = document.querySelectorAll(".dot");

    if (index > slides.length - 1)
        index = 0;

    if (index < 0)
        index = slides.length - 1;

    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].classList.remove("active_dot");
    }

    slides[index].style.display = "block";
    dots[index].classList.add("active_dot");
}
// Image Slider js End (圖片滑動顯示)


//---------------------------------------元素-------------------------------------------------------
const prodName_el = document.querySelector("#prodName");
const prodObj_el = document.querySelector("#prodObj");
const prodPrice_el = document.querySelector("#prodPrice");
const shipQty_el = document.querySelector("#shipQty");
const stockQty_el = document.querySelector("#stockQty");
const prodNo_el = document.querySelector("#prodNo");
const prodText_el = document.querySelector("#prodText");

const objName_el = document.querySelector("#objName");
const objPrice_el = document.querySelector("#objPrice");

let paProdPicNoArray = [];
let count = 0;

//---------------------------------------fetch-------------------------------------------------------

//網址切換part
const queryString = window.location.search;
const urlParms = new URLSearchParams(queryString);
let prodId = urlParms.get("prodId");
const full = location.protocol + '//' + location.host;
loadProdDetail(prodId); // 傳入商品編號

// 載入頁面時讀取商品詳細資訊
function loadProdDetail(opaProdNo) {
    const paProdList_el = $("#paProdList");

    paProdList_el.children().remove();

    paProdPicNoArray = [];

    fetch("/BuyGo/api/opa/prod/selectById",
        {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8", },
            body: JSON.stringify({
                opaProdNo: opaProdNo
            })
        })
        .then(resp => resp.json())
        .then(prod => {
            prodNo_el.append(opaProdNo);
            prodObj_el.append(prod.prcats.opaPrcatsName);
            prodPrice_el.append(`$ ` + prod.opaProdPrice);
            shipQty_el.append(prod.opaProdShipQty);
            stockQty_el.append(prod.opaProdStockQty);
            prodName_el.append(prod.opaProdName);
            prodText_el.append(prod.opaProdContent);

            slides = document.querySelectorAll(".slide2");
            dots = document.querySelectorAll(".dot");

            //圖片載入
            if (prod.prpicsList.length !== 0) {
                let count_dot = 0;
                for (let opaPrPic of prod.prpicsList) {
                    count_dot++;
                    //注意要將.slide 改成.slide2
                    var html = `
                            <div class="slide2">
                                <img src="${opaPrPic.opaProdPicture}" >
                            </div>
                        `;
                    $("#slider").append(html);
                }

                //代表圖片數量的點點 Start (圖片滑動顯示)
                for (let d = 1; d <= count_dot; d++) {
                    var dot_html = `
                            <span class="dot"></span>
                        `;
                    $("#dots-con").append(dot_html);
                }
                var slides = document.querySelectorAll(".slide2"); //注意要將.slide 改成.slide2
                var dots = document.querySelectorAll(".dot");
                changeSlide(slides);
                //代表圖片數量的點點 End (圖片滑動顯示)
            } else {
                $("#img_main_view").attr("src", "../../../../img/common/Image_not_available.png");
            }

            getRanRecommendProds(opaProdNo, prod.opaPrcatsNo);
        });
}

function getRanRecommendProds(opaProdNo, opaPrcatsNo) {
    //用商品類別抓4個商品資訊
    const ranRecommend_el = $("#ranRecommend");
    let count = 0;

    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "getRandomProdsByPrcatsWithLimit",
            opaProdNo: opaProdNo,
            opaPrcatsNo: opaPrcatsNo,
            limit: 4,
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
                                    <li><a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}" class="circle" data-toggle="lightbox"> <i class="iconfont-search"></i>
                                        </a></li>
                                    <li><a href="#" class="circle add-to-cart" onclick="addToCart(${data.opaProdNo});"> <i
                                                class="iconfont-shopping-cart"></i>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="entry-main" style="border-bottom-left-radius: 10px; border-bottom-right-radius: 10px;">
                            <h5 class="entry-title rec-entry-title">
                                <a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}">${data.opaProdName}</a>
                            </h5>
                            <div class="entry-description visible-list">
                                <p>${data.opaProdName}</p>
                            </div>
                            <div class="entry-price">
                                <strong class="price" style="color: #fa6f57; font-weight: bold;">$ ${data.opaProdPrice}</strong>
                            </div>
                        </div>
                    </div>
                `;
                ranRecommend_el.append(productDiv);
            }
        });
}

function addToCart(opaProdNo) {
    if(opaProdNo === 0){
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
                                <a href="#" data-toggle="lightbox" class="entry-thumbnail">
                                    <img src="${prpics_url}" alt="${data.prod.opaProdName}">
                                </a>
                                <h5 class="entry-title cart-entry-title"><a href="#">${data.prod.opaProdName}</a></h5>
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