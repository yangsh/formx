$(document).ready(function() {

    $.ajax({
        method: "GET",
        url: "/mcl/shopkeeper/shop/notcomplete/list",
        data: {}
    }).done(function(res) {
        if (res.code = '0') {
            var list = res.data;
            var lis = '';
            for (var i = 0; i < list.length; i++) {
                lis += '<li class="list" onclick="toFormPage(' + list[i].shopId + ');"><span style="color:#444;">' + list[i].shopName + '</span> <span>( ' + list[i].formCompleteCount + ' )</span>';
                lis += '<span style="float:right;color:#d20c0c;">未完成</span></li>';
            }
            $('#shop_list').html(lis);
        } else {
            alert(res.message);
        }
    });

});

function toFormPage(shopId) {
    window.location.href = '../../form/adviser/notcompletelist.html?shopId=' + shopId;
}