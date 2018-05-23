package com.weimi.formx.michelin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yangsh on 2018-05-20
 */
@Getter
@Setter
@ToString
public class CompleteShopListForShopkeeperResponse {

    private Integer shopId; // 店铺ID
    private String shopName; // 店铺名称
    private String confirmStatus; // 确认状态: 0-未确认; 1-已确认

}
