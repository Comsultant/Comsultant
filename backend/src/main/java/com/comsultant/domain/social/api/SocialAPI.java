package com.comsultant.domain.social.api;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.auth.dto.AuthDto;
import com.comsultant.domain.auth.service.AuthService;
import com.comsultant.domain.social.entity.SnsTypeCode;
import com.comsultant.domain.social.service.SocialService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.properties.SocialProperties;
import com.comsultant.global.util.CookieUtil;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.KakaoApi;
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
            CookieUtil.setRefreshTokenCookie(httpResponse, dto
                    .getRefreshToken());
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));

        } catch (NullPointerException e) {
            log.error("NullPointerException");
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        }
    }

    @GetMapping("/kakao")
    public ResponseEntity<DtoResponse<AuthDto>> kakaoRedirect(@RequestParam("code") String code, HttpServletResponse httpResponse) throws IOException, ExecutionException, InterruptedException, ParseException {

        OAuth20Service kService = new ServiceBuilder(socialProperties.getKakaoClientId())
                .apiSecret(socialProperties.getKakaoClientSecret())
                .build(KakaoApi.instance());

        OAuth2AccessToken accessToken = kService.getAccessToken(code);
        OAuthRequest request = new OAuthRequest(Verb.GET, socialProperties.getKakaoApiUrl());
        kService.signRequest(accessToken, request);
        Response response = kService.execute(request);


        // {"id":2005017902,"connected_at":"2021-11-24T04:35:36Z"}
        try {
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject) parser.parse(response.getBody());
            JSONObject juser = (JSONObject)jobj.get("kakao_account");

            String userid = jobj.get("id").toString();

            // TODO : 카카오 정보 제공항목에서 생년 추가 필요
            String birthYear = "2022";
            if(juser != null) {
                String range = juser.get("age_range").toString();
                int age = Integer.parseInt(range.split("~")[0]);
                birthYear = String.valueOf(2022 - age);
            }

            Account account = socialService.checkSignUp(userid, birthYear, SnsTypeCode.KAKAO_SNS_TYPE);
            AuthDto dto = authService.socialSignIn(account);
            CookieUtil.setRefreshTokenCookie(httpResponse, dto
                    .getRefreshToken());
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));

        } catch (NullPointerException e) {
            log.error("NullPointerException");
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        }
    }

    @GetMapping("/google")
    public ResponseEntity<DtoResponse<AuthDto>> googleRedirect(@RequestParam("code") String code, HttpServletResponse httpResponse) throws IOException, ExecutionException, InterruptedException, ParseException {
        OAuth20Service gService = new ServiceBuilder(socialProperties.getGoogleClientId())
                .apiSecret(socialProperties.getGoogleClientSecret())
                .callback(socialProperties.getGoogleCallbackUrl())
                .build(GoogleApi20.instance());


        OAuth2AccessToken accessToken = gService.getAccessToken(code);
        OAuthRequest request = new OAuthRequest(Verb.GET, socialProperties.getGoogleApiUrl());
        gService.signRequest(accessToken, request);
        Response response = gService.execute(request);

        /**
         * {
         *   "sub": "105729499790627604347",
         *   "picture": "https://lh3.googleusercontent.com/a-/AFdZucpH1KfyFymqznCs0e5ls3N8Xnb9fajei_QuEIiF\u003ds96-c",
         *   "email": "yoongh97@gmail.com",
         *   "email_verified": true
         * }
         */
        System.out.println(response.getBody());
        try {
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject) parser.parse(response.getBody());
            String userid = jobj.get("sub").toString();

            // TODO: 생년 기본값 설정해주기
            String birthYear = "2022";

            Account account = socialService.checkSignUp(userid, birthYear, SnsTypeCode.GOOGLE_SNS_TYPE);
            AuthDto dto = authService.socialSignIn(account);
            CookieUtil.setRefreshTokenCookie(httpResponse, dto
                    .getRefreshToken());
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), dto));

        } catch (NullPointerException e) {
            log.error("NullPointerException");
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        }
    }
}
