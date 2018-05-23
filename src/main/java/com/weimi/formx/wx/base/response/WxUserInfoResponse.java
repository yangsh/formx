package com.weimi.formx.wx.base.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
@Setter
public class WxUserInfoResponse {

    private String userId;
    private String deviceId;
    private String errcode;
    private String errmsg;

}
