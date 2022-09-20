package com.comsultant.global.config.security;

import com.comsultant.global.properties.JwtProperties;
import com.comsultant.infra.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {
    private final RedisService redisService;
    private final AccountDetailsService accountDetailsService;
    private final JwtProperties jwtProperties;
    private String secretKey;
    private static final String HEADER_TOKEN_PREFIX = "Bearer ";
    /**
     * Key Encryption
     */
    @PostConstruct
    protected void init() {
        secretKey =  Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public String generateAccessToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + jwtProperties.getAccessTokenExpireTime());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + jwtProperties.getRefreshTokenExpireTime());

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        redisService.setStringValueAndExpire(refreshToken, email, jwtProperties.getRefreshTokenExpireTime());
        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        AccountDetails accountDetails = accountDetailsService.loadUserByUsername(this.getAccountEmail(token));
        return new UsernamePasswordAuthenticationToken(accountDetails.getUsername(), accountDetails.getPassword(), accountDetails.getAuthorities());
    }

    public String getAccountEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith(HEADER_TOKEN_PREFIX)) {
            return bearerToken.substring(HEADER_TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateToken(ServletRequest req, String token) {
        String attrName = "exception";
        try {
            log.debug("[JwtProvider.validateToken(token)]");
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT Signature", e);
            req.setAttribute(attrName, "SignatureException");
            return false;
        } catch (MalformedJwtException e) {
            log.error("Invalid Jwt token", e);
            req.setAttribute(attrName, "MalformedJwtException");
            return false;
        } catch (ExpiredJwtException e) {
            log.error("Expired Jwt token", e);
            req.setAttribute(attrName, "ExpiredJwtException");
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token", e);
            req.setAttribute(attrName, "UnsupportedJwtException");
            return false;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
            req.setAttribute(attrName, "IllegalArgumentException");
            return false;
        } catch (Exception e) {
            log.error("JWT validation Fail", e);
            req.setAttribute(attrName, "Exception");
            return false;
        }
    }
}
