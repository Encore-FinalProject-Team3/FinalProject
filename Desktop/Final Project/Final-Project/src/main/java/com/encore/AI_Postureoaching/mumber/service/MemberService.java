package com.encore.AI_Postureoaching.mumber.service;

import com.encore.AI_Postureoaching.mumber.Member;
import com.encore.AI_Postureoaching.mumber.dto.MemberDto;
import com.encore.AI_Postureoaching.mumber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //로그인
    public Member login(String name){
       return memberRepository.findByName(name).orElseThrow(()->new IllegalArgumentException("회원가입 해주세요!"));
    }
    //회원가입
    public int join(MemberDto memberDto){
        Member members  = new Member();
        members.setName(memberDto.getName());
        members.setPassword(memberDto.getPassword());
        members.setNicName(memberDto.getNicName());
        members.setMemberPhone(memberDto.getMemberPhone());
        members.setMemberGender(memberDto.getMemberGender());
        members.setMemberAddress(memberDto.getMemberAddress());
        members.setMemberImage(memberDto.getMemberImage());
        members.setMember_pay_point(memberDto.getMember_pay_point());
        try {
            memberRepository.save(members);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }



}
