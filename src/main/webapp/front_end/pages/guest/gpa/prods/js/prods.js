function loadPrcats() {
    $("#category-list").html("");
    fetch("/BuyGo/api/opa/prcats/manage", { headers: { "Content-Type": "application/json; charset=utf-8", } })
        .then(resp => resp.json())
        .then(datas => {
            for (let data of datas) {
                let prcats_li = `
                    <li>
                        <input type="checkbox" id="check-${data.opaPrcatsNo}" class="prettyCheckable"
                            data-label="${data.opaPrcatsName}" data-labelPosition="right" value="${data.opaPrcatsName}" />
                    </li>`;
                $("#category-list").append(prcats_li);
            }
        });
}
loadPrcats();

// function addToCart(opaProdNo) {
//     checkLoginStatusShowMemberAcct();
//     let opaCartId = {
//         memberNo: null,
//         opaProdNo: opaProdNo
//     }

//     fetch("/BuyGo/needLoginApi/opa/cart/selectById", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json; charset=utf-8",
//         },
//         body: JSON.stringify({ opaCartId }),
//     })
//         .then(resp => {
//             if (resp.status === 401) {
//                 // 客戶未登入，執行頁面重定向到登入頁
//                 notLoggedInAction();
//             } else if (resp.ok) {
//                 // 請求成功
//                 return resp.json();
//             } else {
//                 alert("錯誤狀態" + resp.status);
//                 return;
//             }
//         })
//         .then(data => {
//             let opaCartProductsQty = 1;
//             if (data !== null) {
//                 opaCartProductsQty = data.opaCartProductsQty + 1;
//             }

//             fetch("/BuyGo/needLoginApi/opa/cart/update", {
//                 method: "POST",
//                 headers: {
//                     "Content-Type": "application/json; charset=utf-8",
//                 },
//                 body: JSON.stringify({
//                     opaCartId: opaCartId,
//                     opaCartProductsQty: opaCartProductsQty
//                 }),
//             })
//                 .then(resp => resp.json())
//                 .then(body => {
//                     const { successful } = body;
//                     if (successful) {
//                         updateCartItem();
//                     }
//                 });
//         });
// }

// function updateCartItem() {
//     fetch("/BuyGo/needLoginApi/opa/cart/selectByMember", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json; charset=utf-8",
//         },
//         // body: JSON.stringify({ opaCartId }),
//     })
//         .then(resp => {
//             if (resp.status === 401) {
//                 // 客戶未登入，不撈取購物車資料
//                 return;
//             } else if (resp.ok) {
//                 // 請求成功
//                 return resp.json();
//             } else {
//                 alert("錯誤狀態" + resp.status);
//                 return;
//             }
//         })
//         .then(datas => {
//             if (datas && datas.length > 0) {
//                 $(".cart-header").addClass("-none");
//                 let cart_li = "";
//                 let prodGrandTotal = 0;
//                 for (let data of datas) {
//                     let prpics_url = "../../../../img/common/Image_not_available.png";
//                     if (data.prod.prpicsList.length !== 0) {
//                         prpics_url = data.prod.prpicsList[0].opaProdPicture;
//                     }
//                     let prodSubTTL = data.prod.opaProdPrice * data.opaCartProductsQty;
//                     prodGrandTotal += prodSubTTL;
//                     cart_li += `
//                         <li id="li_${data.prod.opaProdNo}">
//                             <div class="item clearfix" data-product-id="${data.prod.opaProdNo}"> 
//                                 <button type="button" class="s-close" aria-hidden="true" onclick="removeCartProd(this)">×</button> 
//                                 <a href="#" data-toggle="lightbox" class="entry-thumbnail">
//                                     <img src="${prpics_url}" alt="${data.prod.opaProdName}">
//                                 </a>
//                                 <h5 class="entry-title"><a href="#">${data.prod.opaProdName}</a></h5>
//                                 <span class="entry-price s-entry-price">$ <span class="s-price">${data.prod.opaProdPrice}</span> x <span class="s-qty">${data.opaCartProductsQty}</span></span>
//                             </div>
//                         </li>
//                     `;

//                 }
//                 $(".s-cart-items").html(cart_li);
//                 $(".s-cart-total > .total").html("$ " + prodGrandTotal);
//             }
//         });
// }
// updateCartItem();

// function removeCartProd(btnEl) {
//     let opaProdNo = btnEl.closest(".item").getAttribute("data-product-id");

//     let opaCartId = {
//         memberNo: null,
//         opaProdNo: opaProdNo
//     };
//     fetch("/BuyGo/needLoginApi/opa/cart/remove", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({ opaCartId })
//     })
//         .then(resp => resp.json())
//         .then(body => {
//             if (body.successful) {
//                 $("#li_" + opaProdNo).fadeOut(500, function () {
//                     $(this).remove();
//                     calcuCartGrandTotal();
//                 });
//             }
//         });
// }

// function calcuCartGrandTotal() {
//     let prodEls = document.querySelectorAll(".s-entry-price");
//     let grandTotal = 0
//     for (let prodEl of prodEls) {
//         let price = parseInt(prodEl.querySelector(".s-price").textContent);
//         let qty = parseInt(prodEl.querySelector(".s-qty").textContent);
//         grandTotal += price * qty;
//     }
//     $(".s-cart-total > .total").html("$ " + grandTotal);
// }

// let totalPages;
// let prodTotalQty;
// function loadAllPages() {
//     fetch("/BuyGo/api/opa/prod", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({
//             action: "getProdTotalQty",
//         })
//     })
//         .then(resp => resp.json())
//         .then(data => {
//             prodTotalQty = data;
//             let displayItemQty = $("#displayItemQty").find(":selected").val();
//             totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
//             createPaginator(1);
//             loadPrcats();
//             loadProds(displayItemQty, 0);
//         });
// }
// loadAllPages();

// function createPaginator(nowPageNo) {
//     let page_li = "";
//     if (totalPages <= 8) {
//         for (let i = 1; i <= totalPages; i++) {
//             page_li += `
//                 <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
//             `;
//         }
//         page_li = `
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
//                     <i class="iconfont-angle-left"></i>
//                 </a>
//             </li>
//             ${page_li}
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
//                     <i class="iconfont-angle-right"></i>
//                 </a>
//             </li>
//         `;
//     } else if (nowPageNo <= 5) {
//         for (let i = 1; i <= 6; i++) {
//             page_li += `
//                 <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
//             `;
//         }
//         page_li = `
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
//                     <i class="iconfont-angle-left"></i>
//                 </a>
//             </li>
//             ${page_li}
//             <li>...</li>
//             <li><a href="#" id="page_${totalPages}" onclick="changePage(this)">${totalPages}</a></li>
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
//                     <i class="iconfont-angle-right"></i>
//                 </a>
//             </li>
//         `;
//     } else if (nowPageNo > totalPages - 6) {
//         for (let i = totalPages - 6; i <= totalPages; i++) {
//             page_li += `
//                 <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
//             `;
//         }
//         page_li = `
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
//                     <i class="iconfont-angle-left"></i>
//                 </a>
//             </li>
//             <li><a href="#" id="page_1" onclick="changePage(this)">1</a></li>
//             <li>...</li>
//             ${page_li}
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
//                     <i class="iconfont-angle-right"></i>
//                 </a>
//             </li>
//         `;
//     } else {
//         for (let i = nowPageNo - 2; i <= nowPageNo + 2; i++) {
//             page_li += `
//                 <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
//             `;
//         }
//         page_li = `
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
//                     <i class="iconfont-angle-left"></i>
//                 </a>
//             </li>
//             <li><a href="#" id="page_1" onclick="changePage(this)">1</a></li>
//             <li>...</li>
//             ${page_li}
//             <li>...</li>
//             <li><a href="#" id="page_${totalPages}" onclick="changePage(this)">${totalPages}</a></li>
//             <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
//                     <i class="iconfont-angle-right"></i>
//                 </a>
//             </li>
//         `;
//     }
//     $(".paginator").html(page_li);
//     $("#page_" + nowPageNo).addClass("pageSelected");
// }

const full = location.protocol + '//' + location.host;

function loadProds() {
    fetch("/BuyGo/api/front_end/gpaProd", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body:JSON.stringify({
            action: "getAllGpaProds"
        })
    })
        .then(resp => resp.json())
        .then(datas => {
            $(".products-layout").html("");
            for (let data of datas) {
                let prpics_url = "../../../../img/common/Image_not_available.png";
                if (data.message !== "") {
                    prpics_url = data.message;
                }
                let prod_div = `
                    <div class="product" data-product-id="${data.gpaProdNo}"
                        data-category="${changeObjText(data.gpaCatsNo)}" data-brand="brand1"
                        data-price="${data.gpaFirstPrice}" data-colors="red|blue|black|white" data-size="S|M|L">
                        <div class="entry-media">
                            <img data-src="${prpics_url}"
                                alt="" class="lazyLoad thumb" />
                            <div class="hover">
                                <a href="${full}/BuyGo/front_end/pages/member/gpa/gpaProduct.html?gpaProdNo=${data.gpaProdNo}" class="entry-url"></a>
                                <ul class="icons unstyled">
                                    <li>
                                        <div class="circle ribbon ribbon-sale">Sale</div>
                                    </li>
                                    <li>
                                        <a href="${prpics_url}" class="circle" data-toggle="lightbox">
                                            <i class="iconfont-search"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="circle add-to-cart" onclick="addToCart(${data.gpaProdNo});">
                                            <i class="iconfont-shopping-cart"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="entry-main">
                            <h5 class="entry-title">
                                <a href="${full}/BuyGo/front_end/pages/member/gpa/gpaProduct.html?gpaProdNo=${data.gpaProdNo}">${data.gpaProdName}</a>
                            </h5>
                            <div class="entry-description visible-list">
                                <p>${data.gpaProdContent}</p>
                            </div>
                            <div class="entry-price">
                                <strong class="price">$ ${data.gpaFirstPrice}</strong>
                                <a href="#"
                                    class="btn btn-round btn-default add-to-cart visible-list" onclick="addToCart(${data.gpaProdNo});">加入購物車</a>
                            </div>
                        </div>
                    </div>
                `;
                $(".products-layout").append(prod_div);
            }
            removeJS();
            setTimeout(() => {
                [
                    '../../../../js/common/minified.js',
                    '../../../../js/common/jquery.nouislider.js',
                    '../../../../js/common/jquery.isotope.min.js',
                    'js/products.js'
                ].forEach(function (src) {
                    const script = document.createElement('script');
                    script.src = src;
                    script.async = false;
                    script.classList.add("commonJS");
                    document.head.appendChild(script);
                });
            }, 500);
        });
}
loadProds();

function removeJS() {
    var tags = document.getElementsByTagName('script');
    for (var i = tags.length; i >= 0; i--) { //search backwards within nodelist for matching elements to remove
        if (tags[i] && tags[i].getAttribute('src') != null && tags[i].classList.contains("commonJS"))
            tags[i].parentNode.removeChild(tags[i]); //remove element by calling parentNode.removeChild()
    }
}

//將商品類別的編號轉成文字資訊
function changeObjText(paProdObjNo) {
    switch (paProdObjNo) {
        case 1:
            return '女生衣著';
        case 1:
            return '美食';
        case 3:
            return '伴手禮';
        case 4:
            return '手機平板與周邊';
        case 5:
            return '文創用品';
        case 6:
            return '書籍及雜誌期刊';
        case 7:
            return '男女鞋';
        case 8:
            return '娛樂、收藏';
        case 9:
            return '男生包包與配件';
        case 10:
            return '服務、票券';
        case 11:
            return '男生衣著';
        case 12:
            return '汽機車零件百貨';
        case 13:
            return '嬰幼兒與母親';
        case 14:
            return '3C與筆電';
        case 15:
            return '居家生活';
        case 16:
            return '女姓配件/黃金';
        case 17:
            return '寵物';
        case 18:
            return '戶外/旅行';
        case 19:
            return '運動/健身';
        case 20:
            return '電玩遊戲';
        case 21:
            return '女性包包/精品';
        case 22:
            return '家電影音';
        case 23:
            return '其他類別';
        default:
            return '出現錯誤，請聯繫客服人員';
    }
}

// function changePage(pageEl) {
//     let pageNo = parseInt(pageEl.textContent);
//     createPaginator(pageNo);
//     let displayItemQty = $("#displayItemQty").find(":selected").val();
//     let offset = (pageNo - 1) * displayItemQty;
//     loadPrcats();
//     if(document.querySelector("#input-search").value === ""){
//         loadProds(displayItemQty, offset);
//     }else{
//         loadSearchProds(displayItemQty, offset);
//     }
// }

// function previewPage() {
//     let old_pageNo = parseInt(document.querySelector(".pageSelected").id.substring(5));
//     if (old_pageNo !== 1) {
//         let new_pageNo = old_pageNo - 1;
//         createPaginator(new_pageNo);
//         let displayItemQty = $("#displayItemQty").find(":selected").val();
//         let offset = (new_pageNo - 1) * displayItemQty;
//         loadPrcats();
//         if(document.querySelector("#input-search").value === ""){
//             loadProds(displayItemQty, offset);
//         }else{
//             loadSearchProds(displayItemQty, offset);
//         }
//     }
// }

// function nextPage() {
//     let old_pageNo = parseInt(document.querySelector(".pageSelected").id.substring(5));
//     if (old_pageNo !== totalPages) {
//         let new_pageNo = old_pageNo + 1;
//         createPaginator(new_pageNo);
//         let displayItemQty = $("#displayItemQty").find(":selected").val();
//         let offset = (new_pageNo - 1) * displayItemQty;
//         loadPrcats();
//         if(document.querySelector("#input-search").value === ""){
//             loadProds(displayItemQty, offset);
//         }else{
//             loadSearchProds(displayItemQty, offset);
//         }
//     }
// }

//切換商品預覽模式
$('[data-layout="list"], [data-layout="grid"]').on('click', function (e) {
    e.preventDefault();
    if (!$(this).hasClass('active')) {
        $('.products-layout').toggleClass('grid').toggleClass('list').isotope('reLayout');
        $(this).closest('ul').find('.active').removeClass('active');
        $(this).addClass('active');
    }
});

// let oldDisplayItemQty = $("#displayItemQty").find(":selected").val();
// $("#displayItemQty").on("change", function () {
//     let displayItemQty = $("#displayItemQty").find(":selected").val();
//     totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
//     let old_pageNo = parseInt(document.querySelector(".pageSelected").id.substring(5));
//     let prodNo = parseInt(oldDisplayItemQty) * old_pageNo > parseInt(prodTotalQty) ? parseInt(prodTotalQty) : parseInt(oldDisplayItemQty) * old_pageNo;
//     let new_pageNo = Math.ceil(prodNo / parseInt(displayItemQty));
//     let offset = (new_pageNo - 1) * displayItemQty;
//     createPaginator(new_pageNo);
//     loadPrcats();
//     if(document.querySelector("#input-search").value === ""){
//         loadProds(displayItemQty, offset);
//     }else{
//         loadSearchProds(displayItemQty, offset);
//     }
//     oldDisplayItemQty = displayItemQty;
// });

// function searchBtnClick(){
//     let opaProdName = document.querySelector("#input-search").value;
//     if(opaProdName === ""){
//         loadAllPages();
//     }else{
//         loadSearchPages(opaProdName);
//     }
// }

// function loadSearchPages(opaProdName) {
//     fetch("/BuyGo/api/opa/prod", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({
//             action: "getProdTotalQtySelectByOpaProdName",
//             opaProdName: opaProdName
//         })
//     })
//         .then(resp => resp.json())
//         .then(data => {
//             prodTotalQty = data;
//             let displayItemQty = $("#displayItemQty").find(":selected").val();
//             totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
//             createPaginator(1);
//             loadPrcats();
//             loadSearchProds(displayItemQty, 0);
//         });
// }

// function loadSearchProds(displayItemQty, offset) {
//     let opaProdName = document.querySelector("#input-search").value;
//     fetch("/BuyGo/api/opa/prod", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({
//             action: "findByOpaProdNameWithLimit",
//             opaProdName: opaProdName,
//             limit: displayItemQty,
//             offset: offset
//         })
//     })
//         .then(resp => resp.json())
//         .then(datas => {
//             $(".products-layout").html("");
//             for (let data of datas) {
//                 let prpics_url = "../../../../img/common/Image_not_available.png";
//                 if (data.prpicsList.length !== 0) {
//                     prpics_url = data.prpicsList[0].opaProdPicture;
//                 }
//                 let prod_div = `
//                     <div class="product" data-product-id="${data.opaProdNo}"
//                         data-category="${data.prcats.opaPrcatsName}" data-brand="brand1"
//                         data-price="${data.opaProdPrice}" data-colors="red|blue|black|white" data-size="S|M|L">
//                         <div class="entry-media">
//                             <img data-src="${prpics_url}"
//                                 alt="" class="lazyLoad thumb" />
//                             <div class="hover">
//                                 <a href="../../product.html" class="entry-url"></a>
//                                 <ul class="icons unstyled">
//                                     <li>
//                                         <div class="circle ribbon ribbon-sale">Sale</div>
//                                     </li>
//                                     <li>
//                                         <a href="${prpics_url}" class="circle" data-toggle="lightbox">
//                                             <i class="iconfont-search"></i>
//                                         </a>
//                                     </li>
//                                     <li>
//                                         <a href="#" class="circle add-to-cart" onclick="addToCart(${data.opaProdNo});">
//                                             <i class="iconfont-shopping-cart"></i>
//                                         </a>
//                                     </li>
//                                 </ul>
//                             </div>
//                         </div>
//                         <div class="entry-main">
//                             <h5 class="entry-title">
//                                 <a href="../../product.html">${data.opaProdName}</a>
//                             </h5>
//                             <div class="entry-description visible-list">
//                                 <p>${data.opaProdContent}</p>
//                             </div>
//                             <div class="entry-price">
//                                 <strong class="price">$ ${data.opaProdPrice}</strong>
//                                 <a href="#"
//                                     class="btn btn-round btn-default add-to-cart visible-list" onclick="addToCart(${data.opaProdNo});">加入購物車</a>
//                             </div>
//                         </div>
//                     </div>
//                 `;
//                 $(".products-layout").append(prod_div);
//             }
//             removeJS();
//             setTimeout(() => {
//                 [
//                     '../../../../js/common/minified.js',
//                     '../../../../js/common/jquery.nouislider.js',
//                     '../../../../js/common/jquery.isotope.min.js',
//                     'js/products.js'
//                 ].forEach(function (src) {
//                     const script = document.createElement('script');
//                     script.src = src;
//                     script.async = false;
//                     script.classList.add("commonJS");
//                     document.head.appendChild(script);
//                 });
//             }, 500);
//         });
// }