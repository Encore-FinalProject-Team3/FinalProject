package com.encore.AI_Postureoaching.config;

import com.encore.AI_Postureoaching.mumber.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration // 시큐리티 설정 권한설정
@EnableWebSecurity //시큐리티 활성화 어노테이션  , 기본 스프링 필터체인 에 등록 한다
public class SecurityConfig {

}
