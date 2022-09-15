package com.comsultant.infra.email;

import com.comsultant.infra.email.vo.MailVo;

public interface MailService {
    MailVo createAuthEmail(String email);
    void sendMail(MailVo mailVo);
}
