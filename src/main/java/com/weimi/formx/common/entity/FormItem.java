package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 表单项
 * Created by yangsh on 2018-05-18
 */
@Getter
@Setter
public class FormItem {

    private Long formItemId; // 表单项ID
    private Integer formId; // 表单ID
    private String formItemName; // 表单项名称
    private Integer orderNum; // 排序权重值
    private String actionItem; // 是否操作项: 0-否; 1-是

}
