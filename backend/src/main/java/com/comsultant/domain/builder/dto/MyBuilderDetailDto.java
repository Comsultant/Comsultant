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
public class MyBuilderDetailDto {
    private MyBuilderDto myBuilderDto;
    private List<BuilderProductDetailDto> builderProductDetailDtos;
}