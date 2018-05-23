package com.weimi.formx.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangsh on 2018-05-16
 */
public final class UrlUtils {

    private UrlUtils() {}

    public static Map<String, String> getUrlParam(String url) {
        Map<String, String> param = new HashMap<>();

        if (url == null) {
            return param;
        }

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(url);
        if (strUrlParam == null) {
            return param;
        }

        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                param.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    param.put(arrSplitEqual[0], "");
                }
            }
        }
        return param;
    }

    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

}
