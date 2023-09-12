
let cartData = JSON.parse(sessionStorage.getItem("cart_data"));
console.log(cartData);

$("#prodTotal").text(cartData.cartSubTTL);
$("#discount").text(cartData.opaDiscountAmo);

let itemName = "";
for (let prod of cartData.prods) {
    //載入結帳商品清單
    fetch("/BuyGo/api/opa/prod/selectById",
        {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8", },
            body: JSON.stringify({
                opaProdNo: prod.opaProdNo
            })
        })
        .then(resp => resp.json())
        .then(data => {
            itemName = itemName + data.opaProdName + ", ";
            let img_url = "../../../../img/common/Image_not_available.png";
            if (data.prpicsList.length !== 0) {
                img_url = data.prpicsList[0].opaProdPicture;
            }

            let prod_str = `
                <div class="product">
                    <div class="product-img">
                        <img src="${img_url}">
                    </div>
                    <div class="product-content">
                        <h3>
                            ${data.opaProdName}
                        </h3>
                        <p class="product-text genre" style="margin: 10px 0 10px 0;">${data.prcats.opaPrcatsName}</p>
                        <div style="margin-right: 10px;">
                            <span class="product-text price">$${data.opaProdPrice}</span>
                            <h3 class="pull-right"><small>×${prod.opaCartProdQty}</small></h3>
                        </div>
                    </div>
                </div>
            `;

            $(".products-table").append(prod_str);
        });
}


$("#confirmCheckoutBtn").on("click", function () {

    //結帳，但應該要變成立訂單比較合理
    //CustomField1=給後端判斷用的action，CustomField2=專案的orderId
    fetch("/BuyGo/ecpay/checkout",
        {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8", },
            body: JSON.stringify({
                TotalAmount: cartData.cartGrandTTL,
                TradeDesc: "BuyGo官方代購",
                ItemName: itemName,
                CustomField1: "opaOrderFirstCheckout",
                CustomField2: "orderId"
            })
        }).then(resp => resp.json())
        .then(data => {
            console.log(data);
            window.open(data);
        });;

    //刪除會員購物車資料
    for (let prod of cartData.prods){
        memberCartProdRemove(prod.opaProdNo);
    }
});

//刪除會員購物車資料
function memberCartProdRemove(opaProdNo) {
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
            if (!body.successful) {
                console.log("移除購物車明細失敗");
            }
        });
}