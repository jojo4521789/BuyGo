
let cartData = JSON.parse(sessionStorage.getItem("cart_data"));
console.log(cartData);

$("#prodTotal").text(cartData.cartSubTTL);
$("#discount").text(cartData.opaDiscountAmo);

let opaOrderdetailsArray = [];
let itemName = "BuyGo商品";
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
            itemName = itemName + data.opaProdNo + ", ";
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

            //將結帳資料存至新訂單陣列
            let opaOrderdetailsId = {
                opaSoNo: null,
                opaProdNo: prod.opaProdNo
            }
            let opaOrderdetails = {
                id: opaOrderdetailsId,
                opaProdName: data.opaProdName,
                opaProdPrice: data.opaProdPrice,
                opaOrdQty: prod.opaCartProdQty
            }
            opaOrderdetailsArray.push(opaOrderdetails);
        });
}

//載入會員資料
function getBillInfo() {
    fetch("/BuyGo/needLoginApi/front_end/member/acc/MemberEdit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "show"
        })
    })
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            $("#inputBillName").val(data.memberName);
            $("#inputBillEmail").val(data.memberEmail);
            $("#inputBillPhone").val(data.memberPhone);
            $("#inputBillAddress").val(data.memberAdd);
            $("#memberWalletAmount").text(data.memberWalletAmount);
        });
}
getBillInfo();

//運送資訊同帳單資訊
function shipCheckClick() {
    if (document.querySelector("#checkShipInfo").checked) {
        $("#inputShipName").val($("#inputBillName").val());
        $("#inputShipEmail").val($("#inputBillEmail").val());
        $("#inputShipPhone").val($("#inputBillPhone").val());
        $("#inputShipAddress").val($("#inputBillAddress").val());
    } else {
        $("#inputShipName").val("");
        $("#inputShipEmail").val("");
        $("#inputShipPhone").val("");
        $("#inputShipAddress").val("");
    }
}

//判斷必填欄位
function checkAllData() {
    let msg = "";
    let billRequired_els = document.querySelectorAll(".bill-required");
    let shipRequired_els = document.querySelectorAll(".ship-required");
    for (let billRequired_el of billRequired_els) {
        if (billRequired_el.value === "") {
            msg += "帳單資訊未填寫完整\n";
            break;
        }
    }
    for (let shipRequired_el of shipRequired_els) {
        if (shipRequired_el.value === "") {
            msg += "運送資訊未填寫完整\n";
            break;
        }
    }
    if ($('input[name=deliveryMethod]:checked').length === 0) {
        msg += "未選擇運送方式\n";
    }
    if ($('input[name=paymethod]:checked').length === 0) {
        msg += "未選擇付款方式";
    }
    if ($('input[name=paymethod]:checked').attr("id") === "paymethod3"){
        if(parseInt($("#memberWalletAmount").text()) < parseInt($("#total").text())){
            msg += "錢包餘額不足";
        }
    }
    if (msg !== "") {
        Swal.fire(msg);
        return true;
    } else {
        return false;
    }
}

//結帳
$("#confirmCheckoutBtn").on("click", function () {
    if (checkAllData()) {
        return;
    }
    
    //新增訂單
    createNewOrder();
});

//新增訂單
function createNewOrder() {
    let newOrderData = {
        opaSoStatus: 0,
        opaProdTotal: cartData.cartSubTTL,
        opaDiscount: cartData.opaDiscountAmo,
        opaFirAmount: $("#total").text(),
        opaRealStatus: 0,
        opaBuyName: $("#inputShipName").val(),
        opaBuyTel: $("#inputShipPhone").val(),
        opaBuyAdd: $("#inputShipAddress").val()
    }
    fetch('/BuyGo/needLoginApi/opa/orderCheckout', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
        body: JSON.stringify(newOrderData),
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const { successful } = data;
            if (successful) {
                if (opaOrderdetailsArray != null) {
                    for (let opaOrderdetail of opaOrderdetailsArray) {
                        opaOrderdetail.id.opaSoNo = data.opaSoNo;
                        //新增訂單明細
                        createNewOrderdetails(opaOrderdetail);
                    }
                    if ($('input[name=paymethod]:checked').attr("id") === "paymethod2") {
                        //信用卡付款，轉綠界
                        ecpay(data.opaSoNo);
                    }
                }
            } else {
                Swal.fire("訂單未成立");
            }
        })
        .catch(error => {
            console.error('錯誤：', error);
        });
}

//新增訂單明細
function createNewOrderdetails(opaOrderdetail) {
    fetch('/BuyGo/needLoginApi/opa/orderdetailsCheckout', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
        body: JSON.stringify(opaOrderdetail),
    })
        .then(response => response.json())
        .then(body => {
            const { successful } = body;
            if (!successful) {
                console.log("新增訂單明細失敗");
            }
        });
}

//信用卡付款，轉綠界
function ecpay(opaSoNo) {
    let totalAmount = $("#total").text();
    let opaCouponNo = 0;
    if (cartData.opaCouponNo !== "") {
        opaCouponNo = cartData.opaCouponNo;
    }

    const full = location.protocol + '//' + location.host;
    let clientBackURL = full + "/BuyGo/front_end/pages/member/opa/order.html";

    //CustomField1=給後端判斷用的action，CustomField2=專案的orderId
    fetch("/BuyGo/ecpay/checkout",
        {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8", },
            body: JSON.stringify({
                TotalAmount: totalAmount,
                TradeDesc: "BuyGo官方代購",
                ItemName: itemName,
                ClientBackURL: clientBackURL,
                CustomField1: "opaOrderFirstCheckout",
                CustomField2: opaSoNo,
                CustomField3: opaCouponNo
            })
        }).then(resp => resp.json())
        .then(data => {
            window.location.href = data;
            // window.open(data);
        });
}