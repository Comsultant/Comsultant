package com.comsultant.domain.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyBuilderDto {

    private long idx;
    
    private long accountIdx;

    private String name;

    private String createDate;

    public void updateUserInfo(long accountIdx) {
        this.accountIdx = accountIdx;
    }
}
