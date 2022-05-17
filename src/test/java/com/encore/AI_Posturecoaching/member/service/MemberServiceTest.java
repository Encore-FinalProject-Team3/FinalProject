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
    @Mock MemberRepository memberRepository;

    @Test
    void findOneTest() {
        //given
        Member member = createMember();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        //when
        MemberDto result = memberService.findOne(1L);

        //then
        assertThat(result.getEmail()).isEqualTo(member.getEmail());

    }

//    @Test //admin일 경우
    void deleteTest() {
        //given
        Member member = createMember();
        given(memberRepository.existsById(anyLong())).willReturn(true);

        //when
        memberService.delete("1",1L);
        //then
        verify(memberRepository).deleteById(anyLong());
    }


}