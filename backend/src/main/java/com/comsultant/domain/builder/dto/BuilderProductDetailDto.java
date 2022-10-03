package com.comsultant.domain.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuilderProductDetailDto {
    private long productIdx;
    private int category;
    private String productName;
    private int price;
    private int cnt;
}
