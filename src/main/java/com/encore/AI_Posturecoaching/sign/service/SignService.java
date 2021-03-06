package com.encore.AI_Posturecoaching.sign.service;

import com.encore.AI_Posturecoaching.config.jwt.TokenProvider;
import com.encore.AI_Posturecoaching.exception.LoginFailureException;
import com.encore.AI_Posturecoaching.exception.MemberEmailAlreadyExistsException;
import com.encore.AI_Posturecoaching.exception.MemberMemberNameAlreadyExistsException;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import com.encore.AI_Posturecoaching.sign.dto.SignInRequestDto;
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

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signUp(final SignUpRequestDto signUpRequestDto) {
        if(signUpRequestDto == null || signUpRequestDto.getEmail() == null ) {
            throw new RuntimeException("허가되지 않은 값이 들어왔습니다.");
        }
        if(memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new MemberEmailAlreadyExistsException();
        }
        if(memberRepository.existsByMemberName(signUpRequestDto.getMemberName())) {
            throw new MemberMemberNameAlreadyExistsException();
        }
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        String role = "MEMBER";
        memberRepository.save(
                new Member(signUpRequestDto.getEmail(), encodedPassword, signUpRequestDto.getMemberName(),role)
        );
    }

    @Transactional
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto){
        Member member = memberRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(LoginFailureException::new);;
        if(!passwordEncoder.matches(signInRequestDto.getPassword(), member.getPassword())) {
            throw new LoginFailureException();
        }
        // 토큰 생성
        final String token = tokenProvider.create(member);
        return new SignInResponseDto(token);
    }

    @Transactional
    public void confirm(final SignUpRequestDto signUpRequestDto) {
        if(!memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new RuntimeException("유저가 존재하지 않습니다");
        }
    }
}
