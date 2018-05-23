package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户店铺关系
 * Created by yangsh on 2018-05-18
 */
@Getter
@Setter
public class UserShopRelation {

    private Long id; // 主键
    private String userId; // 用户ID
    private Integer shopId; // 店铺ID
    private String shopName; // 店铺名称

}
