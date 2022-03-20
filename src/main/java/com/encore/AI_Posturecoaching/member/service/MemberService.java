package com.encore.AI_Posturecoaching.member.service;

import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.dto.MemberDto;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto read(Long id) {
        return MemberDto.toDto(memberRepository.findById(id).orElseThrow(MemberNotFoundException::new));
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        String role = member.getRole();
        if(role == "ADMIN"){
            memberRepository.delete(member);
        }else{
            throw new RuntimeException();
        }
    }

    // 멤버 전체 가져오기
//    public ResponseEntity<?> readAll(){}
}
