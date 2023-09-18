jQuery(function($) {
    var table = $("#request-table");
    table.bootstrapTable({
        url: ROOT_URL + "/needLoginApi/member/opa/request",
        pagination: true,
        columns: [{
            field: "opaRequestNo",
            title: "平台委託單編號"
        }, {
            field: "opaRequestStatus",
            title: "平台委託單狀態",
        }, {
            field: "opaRequestStartdate",
            title: "平台委託單日期"
        }, {
            field: 'opaRequestProductsName',
            title: '平台委託商品名稱',
        }, {
            field: 'opaRequestProductsUrl',
            title: '平台委託商品網址',
        }, {
            field: 'opaRequestProductsContent',
            title: '平台委託商品內容',
        }, {
            field: "opaFailedReason",
            title: "平台委託單失敗原因",
        }],
        onLoadError(status, jqXHR) {
            if(status == 401) {
                alert("帳號未登入，將導轉置登入頁面");
                window.location.href = LOGIN_PAGE;
                return;
            }
        }
    });
});

function addRequest(e) {
    e.preventDefault();
    var url = ROOT_URL + "/needLoginApi/member/opa/request";
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data: {
            "opaRequestProductsName": $("#opaRequestProductsName").val(),
            "opaRequestProductsUrl": $("#opaRequestProductsUrl").val(),
            "opaRequestProductsContent": $("#opaRequestProductsContent").val()
        },
        success: function(data) {
            if (data.successful) {
                alert("申請成功");
                window.location.reload();
            } else {
                if(data.message)
                    alert(data.message);
                else
                    alert("申請失敗");
            }
        },
        error: function(e) {
            if(e.status == 401) {
                alert("帳號未登入，將導轉置登入頁面");
                window.location.href = LOGIN_PAGE;
                return;
            }
            alert("申請失敗");
        }
    })
}