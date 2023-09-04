window.OPA_ORDER_STATUS_MAPPING = ['訂單成立', '第一次付款通知', '海外賣家出貨', '官方海外收貨、發貨', '商品抵台', '第二次付款通知', '官方出貨', '買家完成訂單', '未完成取貨', '取消訂單', '訂單委託失敗'];
window.OPA_ORDER_PAY_STATUS_MAPPING = ['第一次未付款', '第一次已付款', '第二次未付款', '第二次已付款'];
window.OPA_REQUEST_STATUS_MAPPING = ['待審核', '審核通過', '審核不通過'];
function makeObjectFromArray(arr, name) {
    var obj = {};
    for (var i = 0; i < arr.length; i++) {
        obj[arr[i]] = arr[i];
    }
    window[name + '_FILTER'] = obj;
    obj = {};
    for (var i = 0; i < arr.length; i++) {
        obj[i] = arr[i];
    }
    window[name] = obj;
    return obj;
}

makeObjectFromArray(window.OPA_ORDER_STATUS_MAPPING, 'OPA_ORDER_STATUS');
makeObjectFromArray(window.OPA_ORDER_PAY_STATUS_MAPPING, 'OPA_ORDER_PAY_STATUS');
makeObjectFromArray(window.OPA_REQUEST_STATUS_MAPPING, 'OPA_REQUEST_STATUS');