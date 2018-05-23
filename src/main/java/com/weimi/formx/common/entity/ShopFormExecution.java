package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 店铺表单执行
 * Created by yangsh on 2018-05-18
 */
@Getter
@Setter
public class ShopFormExecution {

    private Long shopFormExecutionId; // 店铺表单执行ID
    private Integer shopId; // 店铺ID
    private Integer formId; // 表单ID
    private String formName; // 表单名称
    private String formType; // 表单类型: 1-文本; 2-图片
    private String reportStatus; // 汇报状态: 0-未汇报; 1-已汇报
    private String confirmStatus; // 确认状态: 0-未确认; 1-已确认
    private String comment; // 评论
    private Integer orderNum; // 排序权重值
    private Date reportTime; // 汇报时间
    private Date confirmTime; // 确认时间
    private Date createTime; // 创建时间

}
