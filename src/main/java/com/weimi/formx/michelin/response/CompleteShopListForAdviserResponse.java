package com.weimi.formx.michelin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yangsh on 2018-05-22
 */
@Getter
@Setter
@ToString
public class CompleteShopListForAdviserResponse {

    private Integer shopId; // 店铺ID
    private String shopName; // 店铺名称

}
