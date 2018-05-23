$(document).ready(function() {
    var shopId = getUrlParam('shopId');
    $.ajax({
        method: "GET",
        url: "/mcl/adviser/shopformexecution/list",
        data: {
            shopId: shopId
        }
    }).done(function(res) {
        if (res.code = '0') {
            var data = res.data;
            $('title').text(data.shopName);
            var list = data.shopFormExecutionList;
            var lis = '';
            for (var i = 0; i < list.length; i++) {
                if (list[i].formType == '1') {
                    lis += '<li class="list"><a href="textitem.html?shopFormExecutionId=' + list[i].shopFormExecutionId + '"><div>';
                } else if (list[i].formType == '2') {
                    lis += '<li class="list"><a href="picitem.html?shopFormExecutionId=' + list[i].shopFormExecutionId + '"><div>';
                } else {
                    lis += '<li class="list"><a href="#"><div>';
                }
                lis += '<span style="color:#444;">' + list[i].formName + '</span>';

                if (list[i].confirmStatus == '1') {
                    lis += '<span style="float:right;color:#22a901;">已确认</span>';
                } else {
                     lis += '<span style="float:right;color:#d20c0c;">未确认</span>';
                }
                lis += '</div></a></li>';
            }
            $('#form-list').html(lis);
        } else {
            alert(res.message);
        }
    });

});