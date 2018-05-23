$(document).ready(function() {
    var shopFormExecutionId = getUrlParam('shopFormExecutionId');
    var url = window.location.href;
    $.ajax({
        method: "GET",
        url: "/wx/signature",
        data: {
            url: url.split('#')[0]
        }
    }).done(function(res) {
        if (res.code == '0') {
            var data = res.data;
            wx.config({
                beta: true,
                debug: false,
                appId: data.appId,
                timestamp: data.timestamp,
                nonceStr: data.nonceStr,
                signature: data.signature,
                jsApiList: ['chooseImage', 'uploadImage']
            });

            wx.ready(function() {

            });

            wx.error(function(res) {
                alert('error');
            });
        } else {
            alert(res.message);
        }
    });

    $.ajax({
        method: "GET",
        url: "/mcl/shopowner/shopformpicitem/list",
        data: {
            shopFormExecutionId: shopFormExecutionId
        }
    }).done(function(res) {
        if (res.code == '0') {
            var data = res.data;
            var formName = data.formName;
            var reportStatus = data.reportStatus;
            var confirmStatus = data.confirmStatus;
            var comment = data.comment;
            var list = data.shopFormPicItemList;
            $('title').text(formName);
            if (reportStatus == '0') {
                $('#add_pic').html('<li class="li-add-pic" onclick="addPic(' + shopFormExecutionId + ');"><i class="icon-plus"></i> 添加图片</li>');
            } else {
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
            $('#item_pic_title').html('<li><i class="icon-picture"></i> 图片项</li>');
            var liy = '';
            for (var i = 0; i < list.length; i++) {
                liy += '<li><img src="' + list[i].picUrl + '"/></li>';
                if (reportStatus == '1') {
                     if (list[i].picComment == '') {
                         liy += '<li><div id="pic_comment_display" class="orange">暂无评论</div></li>';
                     } else {
                         liy += '<li><div id="pic_comment_display" class="orange">' + list[i].picComment + '</div></li>';
                     }
                }
            }
            $('#item_pic_list').html(liy);
            if (reportStatus == '0') {
                $('#report_btn').html('<li class="li-btn"><button class="btn" onclick="report('+ shopFormExecutionId + ');">提交</button></li>');
            }
        }
    });
});

function addPic(shopFormExecutionId) {
    wx.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'],
        success: function (res) {
            var localIds = res.localIds.toString();
            wx.uploadImage({
                localId: localIds,
                isShowProgressTips: 1,
                success: function (res) {
                    var serverIds = new Array();
                    serverIds[0] = res.serverId;
                    $.ajax({
                        method: "POST",
                        url: "/wx/pic/download",
                        data: {
                            shopFormExecutionId: shopFormExecutionId,
                            serverIds: serverIds.join(',')
                        }
                    }).done(function(res) {
                        if (res.code == '0') {
                            var picUrls = res.data.picUrls
                            $('#item_pic_list').append('<li><img src="' + picUrls[0] + '"/></li>');
                        }
                    });
                }
            });
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