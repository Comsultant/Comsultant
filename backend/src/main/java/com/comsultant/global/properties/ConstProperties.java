package com.comsultant.global.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "const")
public class ConstProperties {
    private final int passwordTokenLength;
    private final int emailAuthLength;
    private final int commentListSize;
    private final int wishListSize;
    private final int productListSize;
    private final int builderListSize;
}
