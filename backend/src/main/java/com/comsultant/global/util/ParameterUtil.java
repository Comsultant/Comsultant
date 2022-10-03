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

    /**
     * 잘못된 값이면 0으로 리턴
     * 0: 신상품 순
     * 1: 낮은 가격 순
     * 2: 높은 가격 순
     * @param desc
     * @return
     */
    public static int checkProductDesc(int desc) {
        if(desc != 0 && desc != 1 && desc != 2){
            return 0;
        } else {
            return desc;
        }
    }
}
