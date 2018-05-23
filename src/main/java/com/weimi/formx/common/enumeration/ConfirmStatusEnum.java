package com.weimi.formx.common.enumeration;

import lombok.Getter;

/**
 * Created by yangsh on 2018-05-20
 */
@Getter
public enum ConfirmStatusEnum {

    NOT_CONFIRM("0", "未确认"),
    HAS_CONFIRM("1", "已确认");

    private String code;
    private String name;

    private ConfirmStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
