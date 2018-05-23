package com.weimi.formx.common.response;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
@ToString
public class ResponseData<T> {

    private String code; // 编码
    private String message; // 提示信息
    private T data;

    private ResponseData(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        if (data != null) {
            this.data = data;
        }
    }

    private ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseData success() {
        return new ResponseData<T>(ResponseCode.SUCCESS, null);
    }

    public static <T> ResponseData success(T data) {
        return new ResponseData<T>(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseData fail(ResponseCode responseCode) {
        return new ResponseData<T>(responseCode, null);
    }

    public static <T> ResponseData fail(ResponseCode responseCode, String message) {
        return new ResponseData(responseCode.getCode(), message);
    }

}
