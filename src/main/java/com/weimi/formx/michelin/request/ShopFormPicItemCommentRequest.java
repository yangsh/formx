package com.weimi.formx.michelin.request;

import com.weimi.formx.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by yangsh on 2018-05-18
 */
@Getter
@ToString
@Setter
public class ShopFormPicItemCommentRequest extends BaseRequest {

    @NotNull(message = "店铺表单图片项ID不能为空")
    private Long shopFormPicItemId;

    private String picComment;

}
