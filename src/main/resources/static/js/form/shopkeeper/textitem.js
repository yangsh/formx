$(document).ready(function() {
    var shopFormExecutionId = getUrlParam('shopFormExecutionId');
    $.ajax({
        method: "GET",
        url: "/mcl/shopkeeper/shopformtextitem/list",
        data: {
            shopFormExecutionId: shopFormExecutionId
        }
    }).done(function(res) {
        if (res.code == '0') {
            var data = res.data;
            var shopId = data.shopId;
            var formName = data.formName;
            var confirmStatus = data.confirmStatus;
            var comment = data.comment;
            var list = data.shopFormTextItemList;
            $('title').text(formName);
            var lix = '';
            if (confirmStatus == '1') {
                lix += '<li>店主确认：<span class="orange">是</span></li>';
            } else {
                lix += '<li>店主确认：<span class="orange">否</span></li>';
            }
            lix += '<li>顾问评论：</li>';
            lix += '<li id="shopform_comment_content">';
            if (comment == '') {
                lix += '<div class="orange">暂无评论</div>';
            } else {
                lix += '<div class="orange">' + comment + '</div>';
            }
            lix += '</li>';
            $('#shopform_comment').html(lix);
            $('#item_text_title').html('<li><i class="icon-reorder"></i> 文本项</li>');
            var liy = '';
            for (var i = 0; i < list.length; i++) {
                if (list[i].actionItem == '1') {
                    if (list[i].executeStatus == '1') {
                        liy += '<li class="ele ok"><i class="icon-ok"></i> ' + list[i].formItemName + '</li>';
                    } else {
                        liy += '<li class="ele">' + list[i].formItemName + '</li>';
                    }
                } else {
                    liy += '<li class="group"><i class="icon-list-ul"></i> <span style="color:#444;">' + list[i].formItemName + '</span></li>';
                }
            }
            if (confirmStatus == '0') {
                liy += '<li class="li-btn"><button class="btn" onclick="confirm('+ shopFormExecutionId +', ' + shopId + ');">确认</button></li>';
            }
            $('#item_text_list').html(liy);
        }
    });

});

function confirm(shopFormExecutionId, shopId) {
    $.ajax({
        method: "POST",
        url: "/mcl/shopformexecution/confirm",
        data: {
            shopFormExecutionId: shopFormExecutionId
        }
    }).done(function(res) {
        if (res.code == '0') {
            window.location.href = 'completelist.html?shopId=' + shopId;
        }
    });
}