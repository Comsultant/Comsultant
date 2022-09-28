package com.comsultant.domain.builder.mapper;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.entity.MyBuilder;

import java.time.LocalDateTime;

public class MyBuilderMapper {

    public static MyBuilderDto toDto(final MyBuilder entity) {
        return MyBuilderDto.builder()
                .idx(entity.getIdx())
                .accountIdx(entity.getAccount().getIdx())
                .name(entity.getName())
                .createDate(entity.getCreateDate().toString())
                .build();
    }


    public static MyBuilder toEntity(final MyBuilderDto dto) {
        if (dto == null) {
            return null;
        }

        MyBuilder.MyBuilderBuilder myBuilder = MyBuilder.builder();

        myBuilder.account(myBuilderDtoToAccount(dto));

        myBuilder.name(dto.getName());
        if (dto.getCreateDate() != null) {
            myBuilder.createDate(LocalDateTime.parse(dto.getCreateDate()));
        }

        return myBuilder.build();
    }

    protected static Account myBuilderDtoToAccount(MyBuilderDto myBuilderDto) {
        if (myBuilderDto == null) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        if (myBuilderDto.getAccountIdx() != 0) {
            account.idx(myBuilderDto.getAccountIdx());
        }

        return account.build();
    }


}
