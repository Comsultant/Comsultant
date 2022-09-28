package com.comsultant.domain.builder.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.MyBuilderDto;

public interface BuilderService {
    //boolean createBuilder(BuilderDto builderDto);

    boolean createMyBuilder(Account account, MyBuilderDto myBuilderDto);

    boolean deleteMyBuilder(Account account, long myBuilderIdx);

}
