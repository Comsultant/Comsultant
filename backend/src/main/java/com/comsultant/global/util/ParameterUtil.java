package com.comsultant.global.util;

import org.apache.commons.lang3.StringUtils;

public class ParameterUtil {

    public static int checkPage(String page) {
        if(!StringUtils.isNumeric(page) || Integer.parseInt(page) < 1) {
            return 1;
        } else {
            return Integer.parseInt(page);
        }
    }

    /**
     * true 값이면 내림차순 정렬
     * @param desc
     * @return
     */
    public static boolean checkDesc(String desc) {
        return !"true".equals(desc);
    }
}
