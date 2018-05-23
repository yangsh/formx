package com.weimi.formx.michelin.request;

import com.weimi.formx.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by yangsh on 2018-05-20
 */
@Getter
@Setter
@ToString
public class ShopIdRequest extends BaseRequest {

    @NotNull(message = "店铺ID不能为空")
    private Integer shopId;

}
