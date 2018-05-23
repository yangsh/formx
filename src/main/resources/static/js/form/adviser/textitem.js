$(document).ready(function() {
    var shopFormExecutionId = getUrlParam('shopFormExecutionId');
    $.ajax({
        method: "GET",
        url: "/mcl/adviser/shopformtextitem/list",
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
            lix += '<li>顾问评论：<span class="operation-icon" onclick="operateComment(' + shopFormExecutionId + ', this);"><i class="icon-edit"></i></span></li>';
            lix += '<li id="shopform_comment_content">';
            if (comment == '') {
                lix += '<div id="shopform_comment_display" class="orange">暂无评论</div>';
            } else {
                lix += '<div id="shopform_comment_display" class="orange">' + comment + '</div>';
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
                    liy += '<li class="group"><i class="icon-list-ul"></i> <span>' + list[i].formItemName + '</span></li>';
                }
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
            if (comment == '') {
                comment = '暂无评论';
            }
            $('#shopform_comment_content').html('<div id="shopform_comment_display" class="orange">' + comment + '</div>');
            ele.attr('class', 'icon-edit');
        });
    }
}