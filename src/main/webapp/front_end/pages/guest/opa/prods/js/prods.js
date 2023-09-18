const full = location.protocol + '//' + location.host;
let opaPrcatsNoList = [];

function loadPrcats(opaPrcatsNoList) {
    $("#category-list").html("");
    fetch("/BuyGo/api/opa/prcats/manage", { headers: { "Content-Type": "application/json; charset=utf-8", } })
        .then(resp => resp.json())
        .then(datas => {
            for (let data of datas) {
                let checkFlag = "";
                if (opaPrcatsNoList && opaPrcatsNoList.length > 0 && opaPrcatsNoList.includes(parseInt(data.opaPrcatsNo))) {
                    checkFlag = "checked";
                }
                let prcats_li = `
                    <li>
                        <input type="checkbox" id="check-${data.opaPrcatsNo}" class="check-prcats"
                            data-label="${data.opaPrcatsName}" data-labelPosition="right" value="${data.opaPrcatsName}" onclick="checkPrcats()" ${checkFlag}/>
                        <label for="check-${data.opaPrcatsNo}">${data.opaPrcatsName}</label>
                    </li>`;
                $("#category-list").append(prcats_li);
            }
        });
}

function addToCart(opaProdNo) {
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
                                <a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.prod.opaProdNo}" class="entry-thumbnail">
                                    <img src="${prpics_url}" alt="${data.prod.opaProdName}">
                                </a>
                                <h5 class="entry-title"><a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.prod.opaProdNo}">${data.prod.opaProdName}</a></h5>
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

let totalPages;
let prodTotalQty;
function loadAllPages() {
    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "getProdTotalQty",
        })
    })
        .then(resp => resp.json())
        .then(data => {
            prodTotalQty = data;
            let displayItemQty = $("#displayItemQty").find(":selected").val();
            totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
            createPaginator(1);
            loadPrcats();
            loadProds(displayItemQty, 0);
        });
}
loadAllPages();

//建立分頁
function createPaginator(nowPageNo) {
    let page_li = "";
    if (totalPages <= 8) {
        for (let i = 1; i <= totalPages; i++) {
            page_li += `
                <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
            `;
        }
        page_li = `
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
                    <i class="iconfont-angle-left"></i>
                </a>
            </li>
            ${page_li}
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
                    <i class="iconfont-angle-right"></i>
                </a>
            </li>
        `;
    } else if (nowPageNo <= 5) {
        for (let i = 1; i <= 6; i++) {
            page_li += `
                <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
            `;
        }
        page_li = `
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
                    <i class="iconfont-angle-left"></i>
                </a>
            </li>
            ${page_li}
            <li>...</li>
            <li><a href="#" id="page_${totalPages}" onclick="changePage(this)">${totalPages}</a></li>
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
                    <i class="iconfont-angle-right"></i>
                </a>
            </li>
        `;
    } else if (nowPageNo > totalPages - 6) {
        for (let i = totalPages - 6; i <= totalPages; i++) {
            page_li += `
                <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
            `;
        }
        page_li = `
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
                    <i class="iconfont-angle-left"></i>
                </a>
            </li>
            <li><a href="#" id="page_1" onclick="changePage(this)">1</a></li>
            <li>...</li>
            ${page_li}
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
                    <i class="iconfont-angle-right"></i>
                </a>
            </li>
        `;
    } else {
        for (let i = nowPageNo - 2; i <= nowPageNo + 2; i++) {
            page_li += `
                <li><a href="#" id="page_${i}" onclick="changePage(this)">${i}</a></li>
            `;
        }
        page_li = `
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="上一頁" onclick="previewPage()">
                    <i class="iconfont-angle-left"></i>
                </a>
            </li>
            <li><a href="#" id="page_1" onclick="changePage(this)">1</a></li>
            <li>...</li>
            ${page_li}
            <li>...</li>
            <li><a href="#" id="page_${totalPages}" onclick="changePage(this)">${totalPages}</a></li>
            <li><a class="round-icon" href="#" data-toggle="tooltip" data-title="下一頁" onclick="nextPage()">
                    <i class="iconfont-angle-right"></i>
                </a>
            </li>
        `;
    }
    $(".paginator").html(page_li);
    $("#page_" + nowPageNo).addClass("pageSelected");
}

//載入商品(不分類)
function loadProds(displayItemQty, offset) {
    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "findAllProdWithLimit",
            limit: displayItemQty,
            offset: offset
        })
    })
        .then(resp => resp.json())
        .then(datas => {
            loadDatas(datas);
        });
}

function removeJS() {
    var tags = document.getElementsByTagName('script');
    for (var i = tags.length; i >= 0; i--) { //search backwards within nodelist for matching elements to remove
        if (tags[i] && tags[i].getAttribute('src') != null && tags[i].classList.contains("commonJS"))
            tags[i].parentNode.removeChild(tags[i]); //remove element by calling parentNode.removeChild()
    }
}

function changePage(pageEl) {
    let pageNo = parseInt(pageEl.textContent);
    createPaginator(pageNo);
    let displayItemQty = $("#displayItemQty").find(":selected").val();
    let offset = (pageNo - 1) * displayItemQty;
    if (opaPrcatsNoList && opaPrcatsNoList.length > 0) {
        loadPrcatsProds(displayItemQty, offset);
    } else if (document.querySelector("#input-search").value === "") {
        loadProds(displayItemQty, offset);
    } else {
        loadSearchProds(displayItemQty, offset);
    }
    loadPrcats(opaPrcatsNoList);
}

function previewPage() {
    let old_pageNo = parseInt(document.querySelector(".pageSelected").id.substring(5));
    if (old_pageNo !== 1) {
        let new_pageNo = old_pageNo - 1;
        createPaginator(new_pageNo);
        let displayItemQty = $("#displayItemQty").find(":selected").val();
        let offset = (new_pageNo - 1) * displayItemQty;
        if (opaPrcatsNoList && opaPrcatsNoList.length > 0) {
            loadPrcatsProds(displayItemQty, offset);
        } else if (document.querySelector("#input-search").value === "") {
            loadProds(displayItemQty, offset);
        } else {
            loadSearchProds(displayItemQty, offset);
        }
        loadPrcats(opaPrcatsNoList);
    }
}

function nextPage() {
    let old_pageNo = parseInt(document.querySelector(".pageSelected").id.substring(5));
    if (old_pageNo !== totalPages) {
        let new_pageNo = old_pageNo + 1;
        createPaginator(new_pageNo);
        let displayItemQty = $("#displayItemQty").find(":selected").val();
        let offset = (new_pageNo - 1) * displayItemQty;
        if (opaPrcatsNoList && opaPrcatsNoList.length > 0) {
            loadPrcatsProds(displayItemQty, offset);
        } else if (document.querySelector("#input-search").value === "") {
            loadProds(displayItemQty, offset);
        } else {
            loadSearchProds(displayItemQty, offset);
        }
        loadPrcats(opaPrcatsNoList);
    }
}

//切換商品預覽模式
$('[data-layout="list"], [data-layout="grid"]').on('click', function (e) {
    e.preventDefault();
    if (!$(this).hasClass('active')) {
        $('.products-layout').toggleClass('grid').toggleClass('list').isotope('reLayout');
        $(this).closest('ul').find('.active').removeClass('active');
        $(this).addClass('active');
    }
});

let oldDisplayItemQty = $("#displayItemQty").find(":selected").val();
$("#displayItemQty").on("change", function () {
    let displayItemQty = $("#displayItemQty").find(":selected").val();
    totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
    let old_pageNo = parseInt(document.querySelector(".pageSelected").id.substring(5));
    let prodNo = parseInt(oldDisplayItemQty) * old_pageNo > parseInt(prodTotalQty) ? parseInt(prodTotalQty) : parseInt(oldDisplayItemQty) * old_pageNo;
    let new_pageNo = Math.ceil(prodNo / parseInt(displayItemQty));
    let offset = (new_pageNo - 1) * displayItemQty;
    createPaginator(new_pageNo);
    if (opaPrcatsNoList && opaPrcatsNoList.length > 0) {
        loadPrcatsProds(displayItemQty, offset);
    } else if (document.querySelector("#input-search").value === "") {
        loadProds(displayItemQty, offset);
    } else {
        loadSearchProds(displayItemQty, offset);
    }
    loadPrcats(opaPrcatsNoList);
    oldDisplayItemQty = displayItemQty;
});

function searchBtnClick() {
    let opaProdName = document.querySelector("#input-search").value;
    if (opaProdName === "") {
        loadAllPages();
    } else {
        loadSearchPages(opaProdName);
    }
}

//載入搜尋的商品分頁標籤
function loadSearchPages(opaProdName) {
    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "getProdTotalQtySelectByOpaProdName",
            opaProdName: opaProdName
        })
    })
        .then(resp => resp.json())
        .then(data => {
            prodTotalQty = data;
            let displayItemQty = $("#displayItemQty").find(":selected").val();
            totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
            createPaginator(1);
            loadPrcats();
            loadSearchProds(displayItemQty, 0);
        });
}

//載入搜尋的商品
function loadSearchProds(displayItemQty, offset) {
    let opaProdName = document.querySelector("#input-search").value;
    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "findByOpaProdNameWithLimit",
            opaProdName: opaProdName,
            limit: displayItemQty,
            offset: offset
        })
    })
        .then(resp => resp.json())
        .then(datas => {
            loadDatas(datas);
        });
}

function loadDatas(datas) {
    $(".products-layout").html("");
    for (let data of datas) {
        let prpics_url = "../../../../img/common/Image_not_available.png";
        if (data.prpicsList.length !== 0) {
            prpics_url = data.prpicsList[0].opaProdPicture;
        }
        let prod_div = `
            <div class="product" data-product-id="${data.opaProdNo}"
                data-category="${data.prcats.opaPrcatsName}" data-brand="brand1"
                data-price="${data.opaProdPrice}" data-colors="red|blue|black|white" data-size="S|M|L">
                <div class="entry-media">
                    <img data-src="${prpics_url}"
                        alt="" class="lazyLoad thumb" />
                    <div class="hover">
                        <a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}" class="entry-url"></a>
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
                                <a href="#" class="circle add-to-cart" onclick="addToCart(${data.opaProdNo});">
                                    <i class="iconfont-shopping-cart"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="entry-main">
                    <h5 class="entry-title">
                        <a href="${full}/BuyGo/front_end/pages/guest/opa/prods/viewProduct.html?prodId=${data.opaProdNo}">${data.opaProdName}</a>
                    </h5>
                    <div class="entry-description visible-list">
                        <p>${data.opaProdContent}</p>
                    </div>
                    <div class="entry-price">
                        <strong class="price">$ ${data.opaProdPrice}</strong>
                        <a href="#"
                            class="btn btn-round btn-default add-to-cart visible-list" onclick="addToCart(${data.opaProdNo});">加入購物車</a>
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
}

// 商品分類篩選
function checkPrcats() {
    opaPrcatsNoList = [];
    const checkPrcats_els = document.querySelectorAll(".check-prcats:checked");
    if (checkPrcats_els.length > 0) {
        for (let checkPrcats_el of checkPrcats_els) {
            opaPrcatsNoList.push(parseInt(checkPrcats_el.id.substring(6)));
        }
        //載入分類的商品分頁標籤
        fetch("/BuyGo/api/opa/prod", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                action: "getProdTotalQtySelectByOpaPrcatsNo",
                opaPrcatsNoList: opaPrcatsNoList
            })
        })
            .then(resp => resp.json())
            .then(data => {
                prodTotalQty = data;
                let displayItemQty = $("#displayItemQty").find(":selected").val();
                totalPages = Math.ceil(parseInt(prodTotalQty) / parseInt(displayItemQty));
                createPaginator(1);
                loadPrcatsProds(displayItemQty, 0);
                loadPrcats(opaPrcatsNoList);
            });
    } else {
        // 沒有勾選分類就載入所有商品
        loadAllPages();
    }
}

//載入有分類的商品
function loadPrcatsProds(displayItemQty, offset) {
    opaPrcatsNoList = [];
    const checkPrcats_els = document.querySelectorAll(".check-prcats:checked");
    for (let checkPrcats_el of checkPrcats_els) {
        opaPrcatsNoList.push(parseInt(checkPrcats_el.id.substring(6)));
    }
    fetch("/BuyGo/api/opa/prod", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "findByOpaPrcatsNoWithLimit",
            opaPrcatsNoList: opaPrcatsNoList,
            limit: displayItemQty,
            offset: offset
        })
    })
        .then(resp => resp.json())
        .then(datas => {
            loadDatas(datas);
        });
}