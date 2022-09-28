package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListDto<T> {
    private List<T> productDtoList;
    private int totalPage;
}
