package com.weimi.formx.wx.base.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
@Setter
public class WxJsapiTicketResponse {

    private String errcode;
    private String errmsg;
    private String ticket;
    private Integer expires_in;

}
