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

fetch("/BuyGo/api/opa/prod/getOnShelfProds", { headers: { "Content-Type": "application/json; charset=utf-8", } })
    .then(resp => resp.json())
    .then(datas => {
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
                        <a href="../../product.html" class="entry-url"></a>
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
                        <a href="../../product.html">${data.opaProdName}</a>
                    </h5>
                    <div class="entry-description visible-list">
                        <p>${data.opaProdContent}</p>
                    </div>
                    <div class="entry-price">
                        <strong class="price">$ ${data.opaProdPrice}</strong>
                        <a href="#"
                            class="btn btn-round btn-default add-to-cart visible-list" onclick="addToCart(${data.opaProdNo});">Add to
                            Cart</a>
                    </div>
                </div>
            </div>`;

            $(".products-layout").append(prod_div);
        }
    });

function addToCart(opaProdNo) {
    checkLoginStatusShowMemberAcct();
    //有登入會員的版本!!
    let memberNo = 1;
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
                alert('帳號未登入，將導轉至登入頁面');
                window.location.href = '/BuyGo/front_end/pages/member/login.html'; // 登入頁面url
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
                        console.log("新增成功");
                    } else {
                        console.log("新增失敗");
                    }
                });
        });

}