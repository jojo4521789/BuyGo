

jQuery(function($) {
    // extract id from query string
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get("id");
    if (id == null) {
        alert("查無訂單");
        return;
    }
    $('#submit').attr('disabled', true);

    // get wallet balance
    $.ajax({
        url: ROOT_URL + "/needLoginApi/member/wallet/balance",
        type: "GET",
        success: function (data) {
            if (!data.successful) {
                data.currentBalance = "Error";
                data.totalBalance = "Error";
                data.pendingTransaction = "Error";
            }
            $("#balance").text(data.currentBalance);
            if (data.successful) {
                $.ajax({
                    url: ROOT_URL + "/needLoginApi/member/order?id=" + id,
                    type: "GET",
                    success: function (order_data) {
                        if(order_data.opaSecAmount > data.currentBalance)
                            $('#submit').text("餘額不足")
                        else
                            $('#submit').attr('disabled', false);
                        $("#price").text(order_data.opaSecAmount);
                    },
                    error: function (e) {
                        if(e.status == 401) {
                            alert("帳號未登入，將導轉置登入頁面");
                            window.location.href = LOGIN_PAGE;
                            return;
                        }
                    }
                });
            }
        },
        error: function (e) {
            if(e.status == 401) {
                alert("帳號未登入，將導轉置登入頁面");
                window.location.href = LOGIN_PAGE;
                return;
            }
        }
    });
})