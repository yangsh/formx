$(document).ready(function() {

    var url = window.location.href;

    $.ajax({
        method: "GET",
        url: "/mcl/user/roleid",
        data: {
            url: url.split('#')[0]
        }
    }).done(function(res) {
        if (res.code = '0') {
            var data = res.data;
            // 店长
            if (data.roleId == '3') {
                shopOwnerIndex();
            } else if (data.roleId == '2') {
                shopKeeperIndex();
            } else if (data.roleId == '1') {
                adviserIndex();
            } else {
                alert("用户角色获取失败");
            }
        } else {
            alert(res.message);
        }
    });
});

// 店长首页
function shopOwnerIndex() {
    $.ajax({
        method: "GET",
        url: "/mcl/shopowner/shopformexecution/list",
        data: {}
    }).done(function(res) {
        if (res.code = '0') {
            var list = res.data;
            $('title').text('今日汇报');
            var lis = '<ul>';
            for (var i = 0; i < list.length; i++) {
                if (list[i].formType == '1') {
                    lis += '<li class="list"><a href="form/shopowner/textitem.html?shopFormExecutionId=' + list[i].shopFormExecutionId + '"><div>';
                } else if (list[i].formType == '2') {
                    lis += '<li class="list"><a href="form/shopowner/picitem.html?shopFormExecutionId=' + list[i].shopFormExecutionId + '"><div>';
                } else {
                    lis += '<li class="list"><a href="#"><div>';
                }
                lis += '<span style="color:#444;">' + list[i].formName + '</span>';
                if (list[i].reportStatus == '1') {
                    lis += '<span style="float:right;color:#22a901;">已汇报</span>';
                } else {
                    lis += '<span style="float:right;color:#d20c0c;">未汇报</span>';
                }
                lis += '</div></a></li>';
            }
            lis += '</ul>';
            $('#shopowner').html(lis);
        }
    });
}

// 店主首页
function shopKeeperIndex() {
    $.ajax({
        method: "GET",
        url: "/mcl/shopkeeper/shop/data",
        data: {}
    }).done(function(res) {
        if (res.code = '0') {
            var data = res.data;
            $('title').text('今日报表');
            var tb = '<table style="width:100%;text-align:center;border-bottom:1px solid #c4c4c4;font-size:22px;color:#444;">';
            tb += '<tbody>';
            tb += '<tr style="height:100px;">';
            tb += '<td style="width:50%;" onclick="toShopkeeperCompleteShopPage();">';
            tb += '<ul>';
            tb += '<li style="color:#14bc13;border-right:1px solid #c4c4c4;">' + data.completeCount + '</li>';
            tb += '<li style="color:#727272;font-size:16px;border-right:1px solid #c4c4c4;">已完成店铺</li>';
            tb += '</ul>';
            tb += '</td>';
            tb += '<td style="width:50%;" onclick="toShopkeeperNotCompleteShopPage();">';
            tb += '<ul>';
            tb += '<li style="color:#e30000;">' + data.notCompleteCount + '</li>';
            tb += '<li style="color:#727272;font-size:16px;">未完成店铺</li>';
            tb += '</ul></td></tr></tbody></table>';
            $('#shopkeeper').html(tb);
        }
    });
}

// 顾问首页
function adviserIndex() {
    $.ajax({
        method: "GET",
        url: "/mcl/adviser/shop/data",
        data: {}
    }).done(function(res) {
        if (res.code = '0') {
            var data = res.data;
            $('title').text('今日报表');
            var tb = '<table style="width:100%;text-align:center;border-bottom:1px solid #c4c4c4;font-size:22px;color:#444;">';
            tb += '<tbody>';
            tb += '<tr style="height:100px;">';
            tb += '<td style="width:50%;" onclick="toAdviserCompleteShopPage();">';
            tb += '<ul>';
            tb += '<li style="color:#14bc13;border-right:1px solid #c4c4c4;">' + data.completeCount + '</li>';
            tb += '<li style="color:#727272;font-size:16px;border-right:1px solid #c4c4c4;">已完成店铺</li>';
            tb += '</ul>';
            tb += '</td>';
            tb += '<td style="width:50%;" onclick="toAdviserNotCompleteShopPage();">';
            tb += '<ul>';
            tb += '<li style="color:#e30000;">' + data.notCompleteCount + '</li>';
            tb += '<li style="color:#727272;font-size:16px;">未完成店铺</li>';
            tb += '</ul></td></tr></tbody></table>';
            $('#adviser').html(tb);
        }
    });
}

function toShopkeeperCompleteShopPage() {
    window.location.href = 'shop/shopkeeper/complete.html';
}

function toShopkeeperNotCompleteShopPage() {
    window.location.href = 'shop/shopkeeper/notcomplete.html';
}

function toAdviserCompleteShopPage() {
    window.location.href = 'shop/adviser/complete.html';
}

function toAdviserNotCompleteShopPage() {
    window.location.href = 'shop/adviser/notcomplete.html';
}