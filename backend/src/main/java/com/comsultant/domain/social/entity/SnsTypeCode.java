package com.comsultant.domain.social.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SnsTypeCode {
    NAVER_SNS_TYPE(1),
    KAKAO_SNS_TYPE(2),
    GOOGLE_SNS_TYPE(3);
    private final int code;
}
