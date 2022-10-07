package com.comsultant.domain.wish.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.wish.dto.WishListDto;
import com.comsultant.global.config.security.AccountDetails;

public interface WishService {

    boolean createLike(Account account, long productIdx);

    boolean deleteLike(Account account, long productIdx);

    WishListDto getLikes(Account account, int page, boolean desc, AccountDetails accountDetails);

    String findName(long idx, int type, AccountDetails accountDetails);
}
