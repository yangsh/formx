package com.weimi.formx.michelin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by yangsh on 2018-05-21
 */
@Getter
@Setter
@ToString
public class ShopFormExecutionListResponse {

    private String shopName; // 店铺名称
    private List<ShopFormExecutionResponse> shopFormExecutionList; // 店铺表单执行列表

}
