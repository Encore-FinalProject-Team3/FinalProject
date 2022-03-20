package com.encore.AI_Posturecoaching.sign.service;

import com.encore.AI_Posturecoaching.config.jwt.TokenProvider;
import com.encore.AI_Posturecoaching.exception.LoginFailureException;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.sign.dto.SignInResponseDto;
import com.encore.AI_Posturecoaching.sign.dto.SignUpRequestDto;
import com.encore.AI_Posturecoaching.sign.repository.SignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Service
public class SignService {

    private final SignRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signUp(final SignUpRequestDto signUpRequestDto) {
        if(signUpRequestDto == null || signUpRequestDto.getEmail() == null ) {
            throw new RuntimeException("Invalid arguments");
        }
        if(memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            log.warn("Email already exists");
            throw new RuntimeException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        String role = "USER";
        memberRepository.save(
                new Member(signUpRequestDto.getEmail(), encodedPassword, signUpRequestDto.getMemberName(),role)
        );
    }

    @Transactional
    public SignInResponseDto signIn(SignUpRequestDto signUpRequestDto){
        Member member = memberRepository.findByEmail(signUpRequestDto.getEmail()).orElseThrow(LoginFailureException::new);;
        if(!passwordEncoder.matches(signUpRequestDto.getPassword(), member.getPassword())) {
            throw new LoginFailureException();
        }
        // 토큰 생성
        final String token = tokenProvider.create(member);
        return new SignInResponseDto(token);
    }


}
