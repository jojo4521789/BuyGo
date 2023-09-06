
let cartData = JSON.parse(sessionStorage.getItem("cart_data"));
console.log(cartData);

$("#prodTotal").text(cartData.cartSubTTL);
$("#discount").text(cartData.opaDiscountAmo);

let itemName = "";
for (let prod of cartData.prods) {
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
            itemName = itemName + data.opaProdName + " ";
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

    fetch("/BuyGo/opa/checkout/ecpay",
        {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8", },
            body: JSON.stringify({
                TotalAmount: cartData.cartGrandTTL,
                TradeDesc: "BuyGo官方代購",
                ItemName: itemName,
            })
        }).then(resp => resp.json())
        .then(data => {
            console.log(data);
            window.open(data);
        });;
});