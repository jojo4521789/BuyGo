sessionStorage.removeItem("store_data");
let buyerNo;
checkLoginStatusShowMemberAcct().then(response => {
    // console.log(response);
})

//代入登入會員
//  let memberNo = buyerNo;
//  let paCartId = {
//      memberNo: memberNo,
//      paProdNo: null
//  }

//用會員編號取得該會員的購物車資訊
fetch("/BuyGo/needLoginApi/pa/cart/selectByMember", {
    method: "POST",
    headers: {
        "Content-Type": "application/json; charset=utf-8",
    },
    // body: JSON.stringify({ paCartId }),
})
    .then(response => {
        if (response.status === 401) {
            // 客戶未登入，執行頁面重定向到登入頁
            notLoggedInAction();
        } else if (response.ok) {
            // 請求成功
            data = response.json();
            return data;
        } else {
            alert("錯誤狀態:" + response.status);
            return;
        }
    })
    .then(datas => {
        if (datas.length > 0) {
            $(".tbl-cart > tbody").html("");
        }
        for (let data of datas) {
            let paPrpics_url = "../../../../img/common/Image_not_available.png";
            if (data.paProd.paProdPic.length !== 0) {
                paPrpics_url = data.paProd.paProdPic[0].paProdPic;
            }
            let paProdSubTTL = data.paProd.paProdPrice * data.paOrdQty;
            let paProd_tr = `
                <tr id="tr_${data.paProd.paProdNo}">
                    <td>
                        <input type="checkbox" class="paProd-check" onclick="paProdCheckBtn()">
                        <a class="entry-thumbnail"
                            href="${paPrpics_url}"
                            data-toggle="lightbox">
                            <img src="${paPrpics_url}" alt="" />
                        </a>
                        <a id="${data.paProd.paProdNo}" class="entry-title" href="../../../guest/product.html">${data.paProd.paProdName}</a>
                    </td>
                    <td><span class="unit-price">$${data.paProd.paProdPrice}</span></td>
                    <td>
                        <div class="qty-btn-group">
                            <button type="button" class="down" onclick="minusQty(this)" onfocus="focusQtyInput(this)" onblur="blurQtyInput(this)"><i
                                    class="iconfont-caret-down inline-middle"></i></button>
                            <input type="text" value="${data.paOrdQty}" onfocus="focusQtyInput(this)" onblur="blurQtyInput(this)"/>
                            <button type="button" class="up" onclick="addQty(this)" onfocus="focusQtyInput(this)" onblur="blurQtyInput(this)"><i
                                    class="iconfont-caret-up inline-middle"></i></button>
                        </div>
                    </td>
                    <td class="hidden-xs"><strong class="text-bold row-total">$${paProdSubTTL}</strong></td>
                    <td class="hidden-xs"><button type="button" class="close" aria-hidden="true" onclick="cartProdRemove(${data.paProd.paProdNo})">×</button>
                    </td>
                </tr>`;

            $(".tbl-cart > tbody").append(paProd_tr);
        }
    });


//商品數量增加
function addQty(el) {
    let input = el.closest(".qty-btn-group").querySelector("input");
    input.value = parseInt(input.value) + 1;
    let qty = parseInt(input.value);

    let price = parseInt(el.closest("tr").querySelector(".unit-price").textContent.substring(1));

    let subTotal = el.closest("tr").querySelector(".row-total");
    subTotal.textContent = "$" + qty * price;

    let paProdNo = el.closest("tr").querySelector(".entry-title").id;
    paPartProdQtyUpdate(paProdNo, qty);
    calcCartAmt();
}

//ok
//商品數量減少
function minusQty(el) {

    let input = el.closest(".qty-btn-group").querySelector("input");
    input.value = parseInt(input.value) - 1;
    let qty = parseInt(input.value);

    let price = parseInt(el.closest("tr").querySelector(".unit-price").textContent.substring(1));

    let subTotal = el.closest("tr").querySelector(".row-total");
    subTotal.textContent = "$" + qty * price;

    let paProdNo = el.closest("tr").querySelector(".entry-title").id;
    if (qty === 0) {
        cartProdRemove(paProdNo);
    } else {
        paPartProdQtyUpdate(paProdNo, qty);
    }
    calcCartAmt();
}


//購物車新增商品時總金額改變
function paPartProdQtyUpdate(paProdNo, paOrdQty) {
    let paCartId = {
        memberNo: null,
        paProdNo: paProdNo
    };

    fetch("/BuyGo/needLoginApi/pa/cart/update", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({
            paCartId: paCartId,
            paOrdQty: paOrdQty
        }),
    })
        .then(resp => resp.json())
        .then(body => {
            const { successful } = body;
            if (successful) {
                console.log("更新購物車成功");
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

$(".paProd-check-all").on("click", function () {
    $(".paProd-check").prop("checked", $(".paProd-check-all").prop("checked"));
    calcCartAmt();
    $("#noCheckoutItemMsg").addClass("-none");
});

function paProdCheckBtn() {
    $(".paProd-check-all").prop("checked", $(".paProd-check:checked").length === $(".paProd-check").length);
    calcCartAmt();
    $("#noCheckoutItemMsg").addClass("-none");
}

function calcCartAmt() {
    let subTTL = 0;
    // let GrandTTL = 0;
    let check_els = document.querySelectorAll(".paProd-check:checked");
    for (let check_el of check_els) {
        let paProdSubTTL = check_el.closest("tr").querySelector(".row-total").textContent.substring(1);
        subTTL += parseInt(paProdSubTTL);
    }

    // if (usedCoupon_opaMinAmount !== 0 && usedCoupon_opaMinAmount > subTTL) {
    //     $("#selectCouponBtn").text("選擇優惠券");
    //     $("#selectCouponBtn").css("background-color", "#fa6f57");
    //     $("#selectedCouponName").text("");
    //     $(".couponDiscount").addClass("-none");
    //     usedCoupon_opaMinAmount = 0;
    //     usedCoupon_opaDiscountAmo = 0;
    // }

    // GrandTTL = subTTL - usedCoupon_opaDiscountAmo;
    $(".shopcart-total > .cart-subtotal > span.pull-right").text("$" + subTTL);
    // $(".shopcart-total > .cart-total > span.pull-right").text("$" + GrandTTL);
}

//ok
//用商品編號將商品移除購物車
function cartProdRemove(paProdNo) {
    let paCartId = {
        memberNo: null,
        paProdNo: paProdNo
    };
    fetch("/BuyGo/needLoginApi/pa/cart/removeProd", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ paCartId })
    })
        .then(resp => resp.json())
        .then(body => {
            if (body.successful) {

                $("#tr_" + paProdNo).fadeOut(1000, function () {
                    $(this).remove();
                });
            }
        });
}


//購物車結帳操作
function checkoutClick() {
    let cartSubTTL = parseInt($(".shopcart-total > .cart-subtotal > span.pull-right").text().substring(1));
    // let cartGrandTTL = parseInt($(".shopcart-total > .cart-total > span.pull-right").text().substring(1));
    if (cartSubTTL === 0) {
        $("#noCheckoutItemMsg").removeClass("-none");
        return;
    }

    let paProds = [];
    let check_els = document.querySelectorAll(".paProd-check:checked");
    for (let check_el of check_els) {

        let paProdNo = check_el.closest("tr").id.substring(3);
        let paProdName = $(`#${paProdNo}`).text();
        let paProdPrice = check_el.closest("tr").querySelector(".unit-price").textContent.substring(1);
        let paOrdQty = check_el.closest("tr").querySelector(".qty-btn-group > input").value;
        let paProd = {
            paProdNo: paProdNo,
            paProdName: paProdName,
            paProdPrice: paProdPrice,
            paOrdQty: paOrdQty
        }
        paProds.push(paProd);
    }
    let cartData = {
        paProds: paProds,
        cartSubTTL: cartSubTTL,
        // cartGrandTTL: cartGrandTTL
    }
    console.log(cartData);
    sessionStorage.setItem("cart_data", JSON.stringify(cartData));
    window.open("../order/CheckOut.html");
}
