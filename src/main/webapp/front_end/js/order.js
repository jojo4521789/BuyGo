jQuery(function($) {
    $.ajax({
        url: ROOT_URL + "/needLoginApi/member/orders?type=map",
        type: "GET",
        dataType: "json",
        success: function(data) {
            var target = $("#order_tab");
            var tabs = target.find("ul");
            var panels = target.find("div");
            for (var i = 0; i < data.length; i++) {
                var li = $("<li></li>").addClass(i == 0 ? "active" : "");
                var a = $("<a></a>").attr("href", "#tab-" + i).attr("data-toggle", "tab").text(data[i]);
                li.append(a);
                tabs.append(li);

                var div = $("<div></div>").addClass("tab-pane fade").addClass(i == 0 ? "in active" : "").attr("id", "tab-" + i);
                var table = $("<table></table>").addClass("table_style").addClass("table").addClass("table-hover");
                div.append(table);
                panels.append(div);
                table.bootstrapTable({
                    url: ROOT_URL + "/needLoginApi/member/orders?type=order&id=" + i,
                    columns: [{
                        field: "opaSoNo",
                        title: "平台訂單編號"
                    }, {
                        field: "opaSoStatus",
                        title: "平台訂單狀態",
                    }, {
                        field: "opaSoDate",
                        title: "平台訂單日期"
                    }, {
                        field: "opaProdTotal",
                        title: "平台商品總金額"
                    }, {
                        field: "opaDiscount",
                        title: "優惠券折抵金額"
                    }, {
                        field: "opaFirAmount",
                        title: "第一次付款金額"
                    }, {
                        field: "opaSecAmount",
                        title: "第二次付款金額"
                    }, {
                        field: "opaTotal",
                        title: "平台訂單總金額"
                    }, {
                        field: "opaRealTotal",
                        title: "實際付款金額"
                    }, {
                        field: "opaRealStatus",
                        title: "實際付款狀態",
                    }]
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
});