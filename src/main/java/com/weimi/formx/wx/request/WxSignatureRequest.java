package com.weimi.formx.wx.request;

import com.weimi.formx.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
@Setter
@ToString
public class WxSignatureRequest extends BaseRequest {

    @NotEmpty(message = "URL不能为空")
    private String url;

}
