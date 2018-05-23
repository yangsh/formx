package com.weimi.formx.michelin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yangsh on 2018-05-20
 */
@Getter
@Setter
@ToString
public class ShopDataResponse {

    private Integer completeCount; // 已完成数量
    private Integer notCompleteCount; // 未完成数量

}
