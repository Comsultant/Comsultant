package com.comsultant.infra.email.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MailVo {
    String toAddress;
    String title;
    String message;
    String fromAddress;
}
