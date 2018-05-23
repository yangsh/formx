$(document).ready(function() {

    $.ajax({
        method: "GET",
        url: "/mcl/shopkeeper/shop/complete/list",
        data: {}
    }).done(function(res) {
        if (res.code = '0') {
            var list = res.data;
            var lis = '';
            for (var i = 0; i < list.length; i++) {
                lis += '<li class="list" onclick="toFormPage(' + list[i].shopId + ');"><span style="color:#444;">' + list[i].shopName + '</span>';
                if (list[i].confirmStatus == '1') {
                    lis += '<span style="float:right;color:#22a901;">完成确认</span>';
                } else {
                    lis += '<span style="float:right;color:#d20c0c;">待确认</span>';
                }
                lis += '</li>';
            }
            $('#shop_list').html(lis);
        } else {
            alert(res.message);
        }
    });
});

function toFormPage(shopId) {
    window.location.href = '../../form/shopkeeper/completelist.html?shopId=' + shopId;
}