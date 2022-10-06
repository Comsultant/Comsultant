package com.comsultant.domain.builder.service;

import com.comsultant.domain.builder.dto.RecommendBuilderDto;
import com.comsultant.domain.builder.dto.RecommendBuilderResponseDto;
import com.comsultant.global.config.security.AccountDetails;

import java.util.List;

public interface RecommendService {
    List<RecommendBuilderResponseDto> getRecommendBuilder(AccountDetails accountDetails, RecommendBuilderDto recommendBuilderDto);
    List<RecommendBuilderResponseDto> getPopularBuilder();
}
