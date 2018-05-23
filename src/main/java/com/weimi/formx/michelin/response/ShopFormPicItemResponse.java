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
public class ShopFormPicItemResponse {

    private Long shopFormPicItemId; // 店铺表单图片项ID
    private Long shopFormExecutionId; // 店铺表单执行ID
    private String picUrl; // 图片URL
    private String picComment; // 图片评论

}
