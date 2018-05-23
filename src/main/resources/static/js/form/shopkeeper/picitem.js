$(document).ready(function() {
    var shopFormExecutionId = getUrlParam('shopFormExecutionId');
    $.ajax({
        method: "GET",
        url: "/mcl/shopkeeper/shopformpicitem/list",
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
            $('title').text(data.formName);
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
            $('#item_pic_title').html('<li><i class="icon-picture"></i> 图片项</li>');
            var liy = '';
            for (var i = 0; i < list.length; i++) {
                liy += '<li><img src="' + list[i].picUrl + '"/></li>';
                liy += '<li>评论：<span class="operation-icon" onclick="operatePicComment(' + list[i].shopFormPicItemId + ', this);"><i class="icon-edit"></i></span></li>'
                if (list[i].picComment == '') {
                    liy += '<li><div class="orange">暂无评论</div></li>';
                } else {
                    liy += '<li><div class="orange">' + list[i].picComment + '</div></li>';
                }
            }
            $('#item_pic_list').html(liy);
            if (confirmStatus == '0') {
                $('#confirm_btn').html('<li class="li-btn"><button class="btn" onclick="confirm('+ shopFormExecutionId + ', ' + shopId + ');">确认</button></li>');
            }
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

function operatePicComment(shopFormPicItemId, _this) {
    var ele = $(_this).children('i');
    var li_comment = $(_this).parent().next();

    var c = ele.attr('class');
    if (c == 'icon-edit') {
        var li_comment_display = li_comment.find('div');
        var picComment = li_comment_display.text();
        if (picComment == '暂无评论') {
            picComment = '';
        }
        li_comment.html('<textarea class="orange" rows="4">' + picComment + '</textarea>');
        ele.attr('class', 'icon-save');
    } else if (c == 'icon-save') {
        var li_comment_edit = li_comment.find('textarea');
        var picComment = li_comment_edit.val();
        $.ajax({
            method: "POST",
            url: "/mcl/shopformpicitem/comment",
            data: {
                shopFormPicItemId: shopFormPicItemId,
                picComment: picComment
            }
        }).done(function(res) {
            if (res.code == '0') {
                if (picComment == '') {
                    picComment = '暂无评论';
                }
                li_comment.html('<div class="orange">' + picComment + '</div>');
                ele.attr('class', 'icon-edit');
            }
        });
    }
}