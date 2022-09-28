package com.comsultant.global.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    CPU(1, "CPU"),
    RAM(2, "Memory"),
    HDD(3, "HDD"),
    SSD(4, "SSD"),
    PSU(5, "Power Supplier"),
    COOLER(6, "Custom and etc"),
    CASES(7, "Computer Case"),
    MB(8, "Main Board"),
    VGA(9, "Graphic Card");


    private final int idx;
    private final String category;
}
