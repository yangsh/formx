package com.weimi.formx.michelin.request;

import com.weimi.formx.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by yangsh on 2018-05-22
 */
@Getter
@Setter
@ToString
public class ShopFormExecutionCommentRequest extends BaseRequest {

    @NotNull(message = "店铺表单执行ID不能为空")
    private Long shopFormExecutionId;

    private String comment;

}
