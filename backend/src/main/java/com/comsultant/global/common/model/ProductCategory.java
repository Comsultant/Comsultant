package com.comsultant.global.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    CPU(1, "cpu"),
    RAM(2, "ram"),
    HDD(3, "hdd"),
    SSD(4, "ssd"),
    PSU(5, "psu"),
    COOLER(6, "cooler"),
    CASES(7, "cases"),
    MB(8, "mainboard"),
    VGA(9, "vga");


    private final int idx;
    private final String category;
}
