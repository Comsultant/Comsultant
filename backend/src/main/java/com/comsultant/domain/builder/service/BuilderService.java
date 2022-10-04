package com.comsultant.domain.builder.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.MyBuilderDetailDto;
import com.comsultant.domain.builder.dto.MyBuilderDetailListDto;
import com.comsultant.domain.builder.dto.MyBuilderDto;

public interface BuilderService {

    MyBuilderDto createMyBuilder(Account account, MyBuilderDto myBuilderDto);

    MyBuilderDetailListDto getMyBuilderDetails(Account account);

    boolean reNameMyBuilder(Account account, long myBuilderIdx, MyBuilderDto myBuilderDto);

    boolean deleteMyBuilder(Account account, long myBuilderIdx);

    boolean captureBuilder(MyBuilderDto myBuilderDto);

    MyBuilderDetailListDto getMyBuilderPageList(Account account, int page);

    MyBuilderDetailDto getBuilder(long myBuilderIdx, Account account);
}
