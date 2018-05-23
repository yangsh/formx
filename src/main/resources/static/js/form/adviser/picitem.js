$(document).ready(function() {
    var shopFormExecutionId = getUrlParam('shopFormExecutionId');
    $.ajax({
        method: "GET",
        url: "/mcl/adviser/shopformpicitem/list",
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
            var list = data.shopFormPicItemList;
            $('title').text(formName);
            var lix = '';
            if (confirmStatus == '1') {
                lix += '<li>店主确认：<span class="orange">是</span></li>';
            } else {
                lix += '<li>店主确认：<span class="orange">否</span></li>';
            }
            lix += '<li>顾问评论：<span class="operation-icon" onclick="operateComment(' + shopFormExecutionId + ', this);"><i class="icon-edit"></i></span></li>';
            lix += '<li id="shopform_comment_content">';
            if (comment == '') {
                lix += '<div id="shopform_comment_display" class="orange">暂无评论</div>';
            } else {
                lix += '<div id="shopform_comment_display" class="orange">' + comment + '</div>';
            }
            lix += '</li>';
            $('#shopform_comment').html(lix);
            $('#item_pic_title').html('<li><i class="icon-picture"></i> 图片项</li>');
            var liy = '';
            for (var i = 0; i < list.length; i++) {
                liy += '<li><img src="' + list[i].picUrl + '"/></li>';
                if (list[i].picComment == '') {
                    liy += '<li><div id="pic_comment_display" class="orange">暂无评论</div></li>';
                } else {
                    liy += '<li><div id="pic_comment_display" class="orange">' + list[i].picComment + '</div></li>';
                }
            }
            $('#item_pic_list').html(liy);
        }
    });
});

function operateComment(shopFormExecutionId, _this) {
    var ele = $(_this).children('i');
    var c = ele.attr('class');
    if (c == 'icon-edit') {
        var comment = $('#shopform_comment_display').text();
        if (comment == '暂无评论') {
            comment = '';
        }
        $('#shopform_comment_content').html('<textarea class="orange" rows="4" id="shopform_comment_edit">' + comment + '</textarea>');
        ele.attr('class', 'icon-save');
    } else if (c == 'icon-save') {
        var comment = $('#shopform_comment_edit').val();
        $.ajax({
            method: "POST",
            url: "/mcl/shopformexecution/comment",
            data: {
                shopFormExecutionId: shopFormExecutionId,
                comment: comment
            }
        }).done(function(res) {
            if (res.code == '0') {
                if (comment == '') {
                    comment = '暂无评论';
                }
                $('#shopform_comment_content').html('<div id="shopform_comment_display" class="orange">' + comment + '</div>');
                ele.attr('class', 'icon-edit');
            }
        });
    }
}