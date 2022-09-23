package com.comsultant.global.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "social")
public class SocialProperties {
    private final String naverClientId;
    private final String naverClientSecret;
    private final String naverApiUrl;
    private final String kakaoClientId;
    private final String kakaoClientSecret;
    private final String kakaoApiUrl;
    private final String googleClientId;
    private final String googleClientSecret;
    private final String googleCallbackUrl;
    private final String googleApiUrl;
}
