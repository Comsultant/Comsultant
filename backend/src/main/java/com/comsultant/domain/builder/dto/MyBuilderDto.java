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
public class MyBuilderDto {

    private long idx;

    private long accountIdx;

    private String name;

    private List<BuilderProductDto> builderProducts;

    private String createDate;

    private boolean kafka;

    private boolean capture;

    private String use;

    private String program;

    public void updateUserInfo(long accountIdx) {
        this.accountIdx = accountIdx;
    }

}
