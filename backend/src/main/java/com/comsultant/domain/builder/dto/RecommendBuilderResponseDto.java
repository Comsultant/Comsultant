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
public class RecommendBuilderResponseDto {
    private HashMap<String, Integer> []prodDetail;
    String priceDate;
    int price;
}
