package com.comsultant.domain.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuilderDto {
    private String _id;

    private String name;

    private String createDate;
}
