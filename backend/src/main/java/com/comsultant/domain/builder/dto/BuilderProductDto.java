package com.comsultant.domain.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuilderProductDto {

    private long idx;

    private long myBuilderIdx;

    private long productIdx;

    private int cnt;

    public void updateMyBuilderInfo(long myBuilderIdx) {
        this.myBuilderIdx = myBuilderIdx;
    }

}
