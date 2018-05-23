package com.weimi.formx.exception;

import com.weimi.formx.common.response.ResponseCode;
import lombok.Getter;

/**
 * Created by yangsh on 2018-05-16
 */
@Getter
public class FormxException extends RuntimeException {

    private ResponseCode responseCode;

    public FormxException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public FormxException(ResponseCode responseCode, String message) {
        super(message);
        responseCode.setMessage(message);
        this.responseCode = responseCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
