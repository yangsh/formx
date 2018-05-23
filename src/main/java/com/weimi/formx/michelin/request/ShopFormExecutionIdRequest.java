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
@Setter
@ToString
public class ShopFormExecutionIdRequest extends BaseRequest {

    @NotNull(message = "店铺表单执行ID不能为空")
    private Long shopFormExecutionId;

}
