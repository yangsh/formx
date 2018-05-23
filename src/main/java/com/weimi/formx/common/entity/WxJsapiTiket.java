package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信 jsapi_tiket
 * Created by yangsh on 2018-05-17
 */
@Getter
@Setter
public class WxJsapiTiket {

    private Long id; // 自增ID
    private String jsapiTiket; // jsapi_tiket
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间

}
