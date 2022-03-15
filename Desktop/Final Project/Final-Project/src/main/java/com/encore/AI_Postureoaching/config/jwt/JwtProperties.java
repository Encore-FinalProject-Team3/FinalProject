package com.encore.AI_Postureoaching.config.jwt;

// 서버 설정 값 아무도 알면안됨
public interface JwtProperties {
    String SECRET = "래현";
    int EXPIRATION_TIME = 864000000; // 10일 (1/100초)
    String TOKEN_PREFIX = "Bearer";
    String HEADER_STRING = "Authorization";
}
