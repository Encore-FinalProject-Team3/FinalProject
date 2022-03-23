package com.encore.AI_Posturecoaching.member.service;

import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.dto.MemberDto;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;
    @Mock
    MemberRepository memberRepository;

    @Test
    void findOne() {
        //given
        Member member = createMember();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        //when
        MemberDto result = memberService.findOne(1L);

        //then
        assertThat(result.getEmail()).isEqualTo(member.getEmail());

    }

    @Test //admin일 경우
    void delete() {
        //given
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(createMember()));
        //when
        memberService.delete(1l);
        //then
        verify(memberRepository).delete(any());
    }

    @Test //admin이 아닐 경우
    void delete2() {
        //given
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(createMember2()));
        //when

        Exception e = assertThrows(Exception.class,
                () -> memberService.delete(1L));//예외가 발생해야 한다.
        System.out.println(e.getMessage());

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("관리자만 삭제할 수 있습니다");
    }
}