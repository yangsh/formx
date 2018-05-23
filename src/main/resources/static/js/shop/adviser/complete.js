$(document).ready(function() {

    $.ajax({
        method: "GET",
        url: "/mcl/adviser/shop/complete/list",
        data: {}
    }).done(function(res) {
        if (res.code = '0') {
            var list = res.data;
            var lis = '';
            for (var i = 0; i < list.length; i++) {
                lis += '<li class="list" onclick="toFormPage(' + list[i].shopId + ');"><span style="color:#444;">' + list[i].shopName + '</span></li>';
            }
            $('#shop_list').html(lis);
        } else {
            alert(res.message);
        }
    });
});

function toFormPage(shopId) {
    window.location.href = '../../form/adviser/completelist.html?shopId=' + shopId;
}