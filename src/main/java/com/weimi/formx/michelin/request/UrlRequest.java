package com.weimi.formx.michelin.request;

import com.weimi.formx.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by yangsh on 2018-05-20
 */
@Getter
@Setter
@ToString
public class UrlRequest extends BaseRequest {

    @NotEmpty(message = "URL不能为空")
    private String url;

}
