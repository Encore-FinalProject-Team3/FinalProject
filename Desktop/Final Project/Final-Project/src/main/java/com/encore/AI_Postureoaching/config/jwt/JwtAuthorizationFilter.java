package com.encore.AI_Postureoaching.config.jwt;

import com.encore.AI_Postureoaching.mumber.repository.MemberRepository;
import com.nimbusds.jwt.JWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//토근 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("header Authorization"+header);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
    
    }
}
