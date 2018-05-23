package com.weimi.formx.wx.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
@Setter
public class WxSignatureResponse {

    private String appId;
    private String nonceStr;
    private String timestamp;
    private String signature;

}
