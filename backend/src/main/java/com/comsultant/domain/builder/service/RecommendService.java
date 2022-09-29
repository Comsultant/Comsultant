package com.comsultant.domain.builder.service;

import com.comsultant.domain.builder.dto.RecommendBuilderDto;
import com.comsultant.global.config.security.AccountDetails;

public interface RecommendService {
    void getRecommendBuilder(AccountDetails accountDetails, RecommendBuilderDto recommendBuilderDto);
}
