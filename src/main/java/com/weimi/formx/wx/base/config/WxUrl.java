package com.weimi.formx.wx.base.config;

/**
 * Created by yangsh on 2018-05-16
 */
public interface WxUrl {

    /** 获取 access_token */
    String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    /** 获取 jsapi_ticket */
    String JSAPI_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";

    /** 获取 user info */
    String USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";

    /** 下载图片 */
    String DOWNLOAD_PIC = "https://qyapi.weixin.qq.com/cgi-bin/media/get";

}
