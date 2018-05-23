package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 店铺表单图片项
 * Created by yangsh on 2018-05-19
 */
@Getter
@Setter
public class ShopFormPicItem {

    private Long shopFormPicItemId; // 店铺表单图片项ID
    private Long shopFormExecutionId; // 店铺表单执行ID
    private String picName; // 图片名称
    private String picComment; // 图片评论
    private Date createTime; // 创建时间

}
