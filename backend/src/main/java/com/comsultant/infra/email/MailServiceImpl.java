package com.comsultant.infra.email;

import com.comsultant.global.properties.EmailProperties;
import com.comsultant.infra.email.vo.MailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final EmailProperties emailProperties;

    private final JavaMailSender mailSender;

    private final String authTitle = "이메일 인증 제목";
    private final String authMessage = "이메일 인증 내용";


    @Override
    public MailVo createAuthEmail(String email) {
        return MailVo.builder()
                .toAddress(email)
                .title(authTitle)
                .message(authMessage)
                .fromAddress(emailProperties.getUsername())
                .build();
    }

    @Async
    @Override
    public void sendMail(MailVo mailVo) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailVo.getToAddress());
        mail.setSubject(mailVo.getTitle());
        mail.setText(mailVo.getMessage());
        mail.setFrom(mailVo.getFromAddress());
        mail.setReplyTo(mailVo.getFromAddress());

        mailSender.send(mail);
        log.info("Email Sending is Finished");
    }
}
