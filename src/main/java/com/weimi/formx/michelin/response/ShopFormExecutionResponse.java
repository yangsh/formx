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
public class ShopFormExecutionResponse {

    private Long shopFormExecutionId; // 店铺表单执行ID
    private String formName; // 表单名称
    private String formType; // 表单类型: 1-文本; 2-图片
    private String reportStatus; // 汇报状态: 0-未汇报; 1-已汇报
    private String confirmStatus; // 确认状态: 0-未确认; 1-已确认

}
