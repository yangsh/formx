package com.weimi.formx.common.response;

import lombok.Getter;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
public enum ResponseCode {

    /** 成功 */
    SUCCESS("0", "成功"),

    /** 服务端错误 */
    EXCEPTION("1000", "服务器异常"),
    DATA_EXCEPTION("1001", "数据异常"),

    /** 客户端错误 */
    PARAM_INCOMPLETE("2000", "参数不完整"),
    PARAM_ERROR("2001", "参数有误"),
    PARAM_TYPE_ERROR("2002", "参数类型有误");

    private String code; // 编码
    private String message; // 消息

    private ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
