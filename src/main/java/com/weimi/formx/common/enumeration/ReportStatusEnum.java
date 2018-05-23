package com.weimi.formx.common.enumeration;

import lombok.Getter;

/**
 * Created by yangsh on 2018-05-18
 */
@Getter
public enum ReportStatusEnum {

    NOT_REPORT("0", "未汇报"),
    HAS_REPORT("1", "已汇报");

    private String code;
    private String name;

    private ReportStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
