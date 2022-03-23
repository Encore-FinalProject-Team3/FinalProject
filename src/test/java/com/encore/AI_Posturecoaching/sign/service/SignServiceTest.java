/*
package com.encore.AI_Posturecoaching.sign.service;

import com.encore.AI_Posturecoaching.config.jwt.JwtAuthenticationFilter;
import com.encore.AI_Posturecoaching.config.jwt.TokenProvider;
import com.encore.AI_Posturecoaching.exception.MemberEmailAlreadyExistsException;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import com.encore.AI_Posturecoaching.sign.dto.SignInResponseDto;
import com.encore.AI_Posturecoaching.sign.dto.SignUpRequestDto;
import com.encore.AI_Posturecoaching.sign.repository.SignRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.lang.model.element.Name;
import javax.validation.constraints.Email;
import java.util.Optional;

import static com.encore.AI_Posturecoaching.factory.dto.SignUpRequestFactory.createSignUpRequest;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignServiceTest {

    @InjectMocks
    SignService signService;
    @Mock
    SignRepository signRepository;
    @Mock
    MemberRepository memberRepository;
    Member member;
    @Mock
    TokenProvider tokenProvider;
    @Mock
    TokenProvider refreshProvider;

    @Mock
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    void signUp() {
        // given

        */
/*Member member = new Member();
        member.setUsername("lee");
        member.setEmail("a@naver.com");
        member.setPassword("1234");*//*


// TODO
        */
/*SignUpRequestDto req = createSignUpRequest("member5@member5.com","123456!","member5");
        Boolean member1 = signRepository.existsByEmail(req.getEmail());
        given(signRepository.existsByEmail("member5@member5.com")).willReturn(true);*//*



 */
/* Member member = new Member();
        member.setMemberName("lee");
        member.setEmail("aa@naver.com");
        member.setPassword("1234");*//*



 */
/*SignUpRequestDto req = createSignUpRequest("member1@member1.com","123456!","member1");
        Optional<Member> member1 = signRepository.findByEmail(req.getEmail());
        given(signRepository.findByEmail("member1@member1.com")).willReturn(Optional.of(member));*//*


        // when
        signService.signUp(req);

        // then
        verify(passwordEncoder).encode(req.getPassword());
        verify(memberRepository).save(any());


    }


    @Test
    void validateSignUpByDuplicateEmailTest() {
       */
/* // given
        given(memberRepository.existsByEmail(anyString())).willReturn(true);

        // when, then
        assertThatThrownBy(() -> signService.signUp(createSignUpRequest("email", "password")))
                .isInstanceOf(MemberEmailAlreadyExistsException.class);*//*

    }


    @Test
    void signIn() {

      */
/*  // given
        given(memberRepository.findByEmail(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
        given(tokenProvider.create(any())).willReturn("access");
        //given(refreshProvider.create(any())).willReturn("refresh");

        // when
        SignInResponseDto responseDto = signService.signIn(createSignUpRequest("email", "password"));

        // then
        assertThat(responseDto.getAccessToken()).isEqualTo("access");
        //assertThat(responseDto.getRefreshToken()).isEqualTo("refresh");*//*

    }
}

*/
