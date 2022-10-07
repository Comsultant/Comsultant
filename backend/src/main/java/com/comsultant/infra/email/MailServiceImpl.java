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

    private final String authTitle = "COMSULTANT 이메일 인증";
    private final String authMessage = "이메일 인증 번호 : ";
    private final String findPasswordTitle = "COMSULTANT 비밀번호 찾기";
    private final String findPasswordMessage = "비밀번호 찾기 토큰 : ";


    @Override
    public MailVo createAuthEmail(String email, String authToken) {
        return MailVo.builder()
                .toAddress(email)
                .title(authTitle)
                .message(authMessage + authToken)
                .fromAddress(emailProperties.getUsername())
                .build();
    }

    @Override
    public MailVo createFindPasswordEmail(String email, String link) {
        log.info(findPasswordMessage + link);
        return MailVo.builder()
                .toAddress(email)
                .title(findPasswordTitle)
                .message(findPasswordMessage + link)
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
