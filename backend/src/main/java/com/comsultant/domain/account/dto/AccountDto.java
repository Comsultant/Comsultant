package com.comsultant.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String email;
    private String nickname;
    private String password;
    private String createDate;
    private String modifyDate;
    private int snsType;
    private int birthYear;
    private String role;

    public void encryptPassword(String password) {
        this.password = password;
    }
}
