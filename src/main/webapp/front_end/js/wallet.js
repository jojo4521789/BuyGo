$().ready(function () {
    $.ajax({
        url: ROOT_URL + "/needLoginApi/member/wallet/balance",
        type: "GET",
        success: function (data) {
            if (!data.successful) {
                data.totalBalance = "Error";
                data.currentBalance = "Error";
                data.pendingTransaction = "Error";
            }
            $("#total").text(data.totalBalance);
            $("#current").text(data.currentBalance);
            $("#pending").text(data.pendingTransaction);
        },
        error: function (e) {
            if(e.status == 401) {
                alert("帳號未登入，將導轉置登入頁面");
                window.location.href = LOGIN_PAGE;
                return;
            }
            $("#total").text("Error");
            $("#current").text("Error");
            $("#pending").text("Error");
        }
    });
    $('#wallet_table').bootstrapTable({
        url: ROOT_URL + '/needLoginApi/member/wallet/history',
        pagination: true,
        columns: [
            {
                field: 'walletDetail',
                title: 'Wallet Detail',
            },
            {
                field: 'walletTime',
                title: 'Wallet Time',
            },
            {
                field: 'walletAmount',
                title: 'Wallet Amount',
            }
        ],
        onLoadError: function(status, jqXHR) {
            if(status == 401) {
                alert("帳號未登入，將導轉置登入頁面");
                window.location.href = LOGIN_PAGE;
            }
        }
    });
    $('#wallet_add').submit(function (event) {
        event.preventDefault();
        var form = event.target;
        var modal = $('#add_wallet_confirm');
        modal.find('input[name="value"]').val(form.value.value);
        modal.find('form').attr('action', ROOT_URL + '/needLoginApi/member/wallet/history');
        modal.modal('show');
    });
    $('#wallet_withdraw').submit(function (event) {
        event.preventDefault();
        var form = event.target;
        var modal = $('#withdraw_wallet_confirm');
        modal.find('input[name="value"]').val(form.value.value);
        modal.find('form').attr('action', ROOT_URL + '/needLoginApi/member/wallet/history');
        modal.modal('show');
    });
    var url = window.location.href;
    var params = url.split('?');
    if(params.length > 1) {
        var message = params[1].split('=');
        if(message[0] =='message') {
            alert(decodeURIComponent(message[1]));
            window.location.href = params[0];
        }
    }
});