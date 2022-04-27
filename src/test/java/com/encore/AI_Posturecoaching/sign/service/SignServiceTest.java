
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.encore.AI_Posturecoaching.factory.dto.SignUpRequestFactory.createSignUpRequestDto;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignServiceTest {

    @InjectMocks SignService signService;
    @Mock MemberRepository memberRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock TokenProvider tokenProvider;

    @Test
    void signUpTest(){
        // given
        SignUpRequestDto signUpRequestDto = createSignUpRequestDto();

        // when
        signService.signUp((signUpRequestDto));

        // then
        verify(passwordEncoder).encode(signUpRequestDto.getPassword());
        verify(memberRepository).save(any());
    }

    @Test
    void 가입이메일중복테스트(){
        // given
        given(memberRepository.existsByEmail(anyString())).willReturn(true);

        // when
        assertThatThrownBy(() -> signService.signUp(createSignUpRequestDto()))
                .isInstanceOf(MemberEmailAlreadyExistsException.class);

        // then
    }

    @Test
    void 가입멤버이름중복테스트(){
        // given
        given(memberRepository.existsByMemberName(anyString())).willReturn(true);

        // when
        assertThatThrownBy(() -> signService.signUp(createSignUpRequestDto()))
                .isInstanceOf(MemberMemberNameAlreadyExistsException.class);

        // then
    }

    @Test
    void signInTest(){
        // given
        given(memberRepository.findByEmail(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(),anyString())).willReturn(true);
        given(tokenProvider.create(any())).willReturn("access");

        // when
        SignInResponseDto signInResponseDto = signService.signIn(new SignInRequestDto("email@email.com","password"));
        System.out.println(signInResponseDto.getAccessToken());
        // then
        assertThat(signInResponseDto.getAccessToken()).isEqualTo("access");
    }

    @Test
    void 로그인이메일실패(){
        // given
        given(memberRepository.findByEmail(any())).willReturn((Optional.empty()));

        // when
        assertThatThrownBy(() -> signService.signIn(new SignInRequestDto("email@email.com","password")))
                .isInstanceOf(LoginFailureException.class);

        // then
    }

    @Test
    void 로그인비밀번호실패(){
        // given
        given(memberRepository.findByEmail(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(),anyString())).willReturn(false);

        // when
        assertThatThrownBy(() -> signService.signIn(new SignInRequestDto("email@email.com","password")))
                .isInstanceOf(LoginFailureException.class);
        // then
    }


}
