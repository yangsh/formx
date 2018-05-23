$(document).ready(function() {
    var shopFormExecutionId = getUrlParam('shopFormExecutionId');
    $.ajax({
        method: "GET",
        url: "/mcl/shopowner/shopformtextitem/list",
        data: {
            shopFormExecutionId: shopFormExecutionId
        }
    }).done(function(res) {
        if (res.code == '0') {
            var data = res.data;
            var formName = res.formName;
            var reportStatus = data.reportStatus;
            var confirmStatus = data.confirmStatus;
            var comment = data.comment;
            var list = data.shopFormTextItemList;
            $('title').text(formName);
            if (reportStatus == '1') {
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
            }
            $('#item_text_title').html('<li><i class="icon-reorder"></i> 文本项</li>');
            var liy = '';
            for (var i = 0; i < list.length; i++) {
                if (list[i].actionItem == '1') {
                    if (list[i].executeStatus == '1') {
                        liy += '<li class="ele ok"><i class="icon-ok"></i> ' + list[i].formItemName + '</li>';
                    } else {
                        if (reportStatus == '1') {
                            liy += '<li class="ele">' + list[i].formItemName + '</li>';
                        } else {
                            liy += '<li class="ele" onclick="execute(this, ' + list[i].shopFormTextItemId + ');">' + list[i].formItemName + '</li>';
                        }
                    }
                } else {
                    liy += '<li class="group"><i class="icon-list-ul"></i> <span style="color:#444;">' + list[i].formItemName + '</span></li>';
                }
            }
            if (reportStatus == '0') {
                liy += '<li class="li-btn"><button class="btn" onclick="report('+ shopFormExecutionId +');">提交</button></li>';
            }
            $('#item_text_list').html(liy);
        }
    });
});

function execute(li, shopFormTextItemId) {
    $.ajax({
        method: "POST",
        url: "/mcl/shopformtextitem/execute",
        data: {
            shopFormTextItemId: shopFormTextItemId
        }
    }).done(function(res) {
        if (res.code == '0') {
            var nli = '<i class="icon-ok"></i> ' + $(li).text();
            $(li).addClass('ok').html(nli).removeAttr('onclick');
        }
    });
}

function report(shopFormExecutionId) {
    $.ajax({
        method: "POST",
        url: "/mcl/shopformexecution/report",
        data: {
            shopFormExecutionId: shopFormExecutionId
        }
    }).done(function(res) {
        if (res.code == '0') {
            window.location.href = '../../index.html';
        }
    });
}