package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信 access_token
 * Created by yangsh on 2018-05-17
 */
@Getter
@Setter
public class WxAccessToken {

    private Long id; // 自增ID
    private String accessToken; // access_token
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间

}
