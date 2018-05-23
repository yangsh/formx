package com.weimi.formx.wx.request;

import com.weimi.formx.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yangsh on 2018-05-19
 */
@Getter
@Setter
@ToString
public class WxPicDownloadRequest extends BaseRequest {

    @NotNull(message = "店铺表单执行ID不能为空")
    private Long shopFormExecutionId;

    @NotEmpty(message = "服务器端ID列表不能为空")
    private List<String> serverIds;

}