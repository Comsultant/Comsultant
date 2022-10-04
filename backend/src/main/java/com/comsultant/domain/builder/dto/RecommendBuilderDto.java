package com.comsultant.domain.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendBuilderDto {

    private String id;
    // 부품을 선택했을 경우, 해당 부품의 idx가 들어갈 배열
    private HashMap<String, Integer> prods;

    // 추천받고 싶은 부품 개수 배열
    // 선택한 부품 개수 미포함
    private int cpu_cnt;
    private int ram_cnt;
    private int hdd_cnt;
    private int ssd_cnt;
    private int psu_cnt;
    private int cooler_cnt;
    private int cases_cnt;
    private int mainboard_cnt;
    private int vga_cnt;

    private String use;
    private String program;

    private int lowPrice;
    private int highPrice;

    private int year;
    private int price;
    private String price_date;

    // 총 카운트
    private int cnt;

}
