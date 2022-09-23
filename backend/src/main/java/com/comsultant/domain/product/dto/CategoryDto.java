package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private long type; // 1.cpu, 2.ram, 3.hdd, 4.ssd, 5.psu, 6.cooler, 7.cases, 8.mainboard, 9.vga

    private String name;
}
