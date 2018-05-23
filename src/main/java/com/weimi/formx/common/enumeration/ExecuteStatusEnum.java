package com.weimi.formx.common.enumeration;

import lombok.Getter;

/**
 * Created by yangsh on 2018-05-18
 */
@Getter
public enum ExecuteStatusEnum {

    NOT_EXECUTE("0", "未执行"),
    HAS_EXECUTE("1", "已执行");

    private String code;
    private String name;

    private ExecuteStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }


}
