package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProductDto {
    private long idx;
    private int category;
    private String name;
    private int imgCnt;
}
