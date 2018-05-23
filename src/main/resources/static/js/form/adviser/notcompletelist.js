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
                lis += '<li class="list"><div><span style="color:#444;">' + list[i].formName + '</span>';
                if (list[i].reportStatus == '1') {
                    lis += '<span style="float:right;color:#22a901;">已汇报</span>';
                } else {
                    lis += '<span style="float:right;color:#d20c0c;">未汇报</span>';
                }
                lis += '</div></li>';
            }
            $('#form-list').html(lis);
        } else {
            alert(res.message);
        }
    });
});