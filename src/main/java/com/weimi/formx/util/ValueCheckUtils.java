package com.weimi.formx.util;

import com.weimi.formx.common.response.ResponseCode;
import com.weimi.formx.exception.FormxException;

/**
 * 校验工具
 * Created by yangsh on 2018-05-18
 */
public class ValueCheckUtils {

    private ValueCheckUtils() {}

    /**
     * 校验字符串不为空
     */
    public static void notEmpty(String value, String message) {
        if (StringUtils.isEmpty(value)) {
            throw new FormxException(ResponseCode.DATA_EXCEPTION, message);
        }
    }

    /**
     * 校验对象不为NULL
     */
    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new FormxException(ResponseCode.DATA_EXCEPTION, message);
        }
    }

}
