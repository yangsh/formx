package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 店铺表单文本项
 * Created by yangsh on 2018-05-18
 */
@Getter
@Setter
public class ShopFormTextItem {

    private Long shopFormTextItemId; // 店铺表单项ID
    private Long shopFormExecutionId; // 店铺表单执行ID
    private Long formItemId; // 表单项ID
    private String formItemName; // 表单项名称
    private Integer orderNum; // 排序权重值
    private String actionItem; // 是否操作项: 0-否; 1-是
    private String executeStatus; // 执行状态: 0-未执行; 1-已执行
    private Date executeTime; // 执行时间
    private Date createTime; // 创建时间

}
