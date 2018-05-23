package com.weimi.formx.michelin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yangsh on 2018-05-21
 */
@Getter
@Setter
@ToString
public class ShopFormTextItemResponse {

    private Long shopFormTextItemId; // 店铺表单项ID
    private Long shopFormExecutionId; // 店铺表单执行ID
    private String formItemName; // 表单项名称
    private String actionItem; // 是否操作项: 0-否; 1-是
    private String executeStatus; // 执行状态: 0-未执行; 1-已执行

}
