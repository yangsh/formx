package com.weimi.formx.michelin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Getter
@Setter
@ToString
public class ShopFormTextItemListResponse {

    private Integer shopId; // 店铺ID
    private String formName; // 表单名称
    private String confirmStatus; // 确认状态: 0-未确认; 1-已确认
    private String comment; // 评论
    private List<ShopFormTextItemResponse> shopFormTextItemList; // 店铺表单文本项列表

}
