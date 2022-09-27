package com.comsultant.global.util;

import org.apache.commons.lang3.StringUtils;

public class ParameterUtil {

    public static int checkPage(String page) {
        if(!StringUtils.isNumeric(page) || Integer.parseInt(page) < 0) {
            return 0;
        } else {
            return Integer.parseInt(page) - 1;
        }
    }

    /**
     * true 값이면 내림차순 정렬
     * @param desc
     * @return
     */
    public static boolean checkDesc(String desc) {
        if(desc == null){
            return true;
        } else {
            return "true".equals(desc);
        }
    }
}
