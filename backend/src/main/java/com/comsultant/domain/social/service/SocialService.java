package com.comsultant.domain.social.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.social.entity.SnsTypeCode;

public interface SocialService {
    Account checkSignUp(String id, String birthYear, SnsTypeCode code);
}
