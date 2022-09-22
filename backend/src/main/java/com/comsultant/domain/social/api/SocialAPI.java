package com.comsultant.domain.social.api;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.auth.dto.AuthDto;
import com.comsultant.domain.auth.service.AuthService;
import com.comsultant.domain.social.entity.SnsTypeCode;
import com.comsultant.domain.social.service.SocialService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.properties.SocialProperties;
import com.github.scribejava.apis.NaverApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/social")
public class SocialAPI {

    private final SocialProperties socialProperties;
    private final ResponseProperties responseProperties;
    private final SocialService socialService;
    private final AuthService authService;

    @GetMapping("/naver")
    public ResponseEntity<DtoResponse<AuthDto>> naverRedirect(@RequestParam("state") String state, @RequestParam("code") String code,
                                                              HttpServletResponse httpResponse) throws IOException, ExecutionException, InterruptedException, ParseException {

        log.info(socialProperties.getNaverClientId());
        log.info(socialProperties.getNaverClientSecret());
        log.info(socialProperties.getNaverApiUrl());

        // 코드 이용해서 접근 토큰 받는다.
        OAuth20Service nService = new ServiceBuilder(socialProperties.getNaverClientId())
                .apiSecret(socialProperties.getNaverClientSecret())
                .build(NaverApi.instance());

        OAuth2AccessToken accessToken = nService.getAccessToken(code);

        // 회원 정보 요청
        OAuthRequest request = new OAuthRequest(Verb.GET, socialProperties.getNaverApiUrl());
        nService.signRequest(accessToken, request);
        Response response = nService.execute(request);

        // {"resultcode":"00","message":"success","response":{"id":"YOkwvifsndbE7PCE2qrtyLEadURmKHepaZqW21A3rso","email":"yoongh97@naver.com","name":"\uc724\uc8fc\uacbd","birthyear":"1997"}}
        try {
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject) parser.parse(response.getBody());
            JSONObject juser = (JSONObject)jobj.get("response");
            String userid = juser.get("id").toString();
            String birthYear = juser.get("birthyear").toString();

            Account account = socialService.checkSignUp(userid, birthYear, SnsTypeCode.NAVER_SNS_TYPE);
            AuthDto dto = authService.socialSignIn(account);
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));

        } catch (NullPointerException e) {
            log.error("NullPointerException");
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        }
    }
}
