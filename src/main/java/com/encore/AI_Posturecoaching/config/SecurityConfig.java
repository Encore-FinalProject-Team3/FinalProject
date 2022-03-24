package com.encore.AI_Posturecoaching.config;



import com.encore.AI_Posturecoaching.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;


@Configuration // 시큐리티 설정 권한설정
@EnableWebSecurity //시큐리티 활성화 어노테이션  , 기본 스프링 필터체인 에 등록 한다
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http.cors() // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정.
                .and()
                .csrf()// csrf는 현재 사용하지 않으므로 disable
                .disable()
                .httpBasic()// token을 사용하므로 basic 인증 disable
                .disable()
                .sessionManagement()  // session 기반이 아님을 선언
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests() // /와 /auth/** 경로는 인증 안해도 됨.
                        .antMatchers(HttpMethod.POST,"/api/signup","/api/signin","/api/confirm").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/members/").authenticated()
                        .antMatchers(HttpMethod.POST,"/api/members/**").authenticated()
                        .antMatchers(HttpMethod.PUT,"./api/members/**").authenticated()
                        .antMatchers(HttpMethod.POST,"/api/categories/**").authenticated()
                        .antMatchers(HttpMethod.GET,"/api/categories/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/board/categories/**").permitAll()
                        .antMatchers(HttpMethod.PUT,"/api/categories/**").authenticated()
                        .antMatchers(HttpMethod.DELETE,"/api/categories/**").authenticated()
                        .antMatchers(HttpMethod.GET,"api/board/**").permitAll()
                        .antMatchers(HttpMethod.POST,"api/board/**").authenticated()
                        .antMatchers(HttpMethod.PUT,"api/board/**").authenticated()
                        .antMatchers(HttpMethod.DELETE,"api/board/**").authenticated()
                        .antMatchers(HttpMethod.GET,"/api/expert/**").permitAll()

                        .anyRequest() // /와 /auth/**이외의 모든 경로는 인증 해야됨.
                .authenticated();

        // filter 등록.
        // 매 리퀘스트마다
        // CorsFilter 실행한 후에
        // jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
