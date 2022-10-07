package com.comsultant.domain.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyBuilderDetailListDto {
    private List<MyBuilderDetailDto> myBuilderDetailDtoList;
    private int totalPage;
}