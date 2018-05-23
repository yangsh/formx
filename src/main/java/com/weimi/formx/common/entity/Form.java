package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 表单
 * Created by yangsh on 2018-05-18
 */
@Getter
@Setter
public class Form {

    private Integer formId; // 表单ID
    private String formName; // 表单名称
    private String formType; // 表单类型: 1-文本; 2-图片
    private Integer orderNum; // 排序权重值

}
