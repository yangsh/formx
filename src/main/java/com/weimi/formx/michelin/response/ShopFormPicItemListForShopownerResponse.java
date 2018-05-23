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
public class ShopFormPicItemListForShopownerResponse {

    private String formName; // 表单名称
    private String reportStatus; // 汇报状态: 0-未汇报; 1-已汇报
    private String confirmStatus; // 确认状态: 0-未确认; 1-已确认
    private String comment; // 评论
    private List<ShopFormPicItemResponse> shopFormPicItemList; // 店铺表单图片项列表

}
