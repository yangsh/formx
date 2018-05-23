package com.weimi.formx.util;

import java.util.Random;

/**
 * Created by yangsh on 2018-05-16
 */
public final class StringUtils {

    private StringUtils() {}

    /**
     * 获取 N 位随机字符串
     */
    public static String getRandomString(final int n) {
        String randString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int number = random.nextInt(randString.length());
            sb.append(randString.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 字符串非空验证
     */
    public static boolean isNotEmpty(final String s) {
        if (s == null) {
            return false;
        }
        return s.trim().length() != 0;
    }

    /**
     * 字符串空验证
     */
    public static boolean isEmpty(final String s) {
        if (s == null) {
            return true;
        }
        return s.trim().length() == 0;
    }

}
