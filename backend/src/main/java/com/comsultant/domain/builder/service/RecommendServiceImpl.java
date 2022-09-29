package com.comsultant.domain.builder.service;

import com.comsultant.domain.builder.dto.RecommendBuilderDto;
import com.comsultant.global.config.security.AccountDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    private final MongoTemplate mongoTemplate;

    @Override
    public void getRecommendBuilder(AccountDetails accountDetails, RecommendBuilderDto recommendBuilderDto) {

    }
}
