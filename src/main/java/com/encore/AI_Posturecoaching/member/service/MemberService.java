package com.encore.AI_Posturecoaching.member.service;

import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.dto.MemberDto;
import com.encore.AI_Posturecoaching.member.dto.MemberUpdateRequestDto;
import com.encore.AI_Posturecoaching.member.dto.MemberUpdateResponseDto;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto findOne(Long id) {
        return MemberDto.toDto(memberRepository.findById(id).orElseThrow(MemberNotFoundException::new));
    }

    public List<MemberDto> findAll() {
        List<MemberDto> memberList = memberRepository.findAll().stream().map(m -> MemberDto.toDto(m)).collect(Collectors.toList());
        return memberList;
    }

    @Transactional
    public MemberUpdateResponseDto update(String memberId, Long id, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member useMember = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new);
        if (Long.valueOf(memberId) == id || useMember.getRole().equals("ADMIN")) {
//        memberRepository.update(memberUpdateRequestDto);
            final Optional<Member> original = memberRepository.findById(id);
            original.ifPresent(newData -> {
                // (3) 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
                if (memberUpdateRequestDto.getPassword() != null) {
                    newData.setPassword(memberUpdateRequestDto.getPassword());
                }
                newData.setMemberName(memberUpdateRequestDto.getMemberName());
                newData.setBrith(memberUpdateRequestDto.getBrith());
                newData.setPhone(memberUpdateRequestDto.getPhone());
                newData.setGender(memberUpdateRequestDto.getGender());
                newData.setAddress(memberUpdateRequestDto.getAddress());
                // (4) 데이터베이스에 새 값을 저장한다.
                memberRepository.save(newData);
            });
        } else {
            throw new RuntimeException("관리자 또는 자신의 정보만 수정 가능합니다");
        }
        return new MemberUpdateResponseDto(useMember.getId());
    }

    @Transactional
    public void delete(String memberId, Long id) {
        String role = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new).getRole();
        if (role.equals("ADMIN")) {
            Member deleteMember = memberRepository.findById(Long.valueOf(id)).orElseThrow(MemberNotFoundException::new);
            memberRepository.delete(deleteMember);
        } else {
            throw new RuntimeException("관리자만 삭제할 수 있습니다");
        }
    }

    // 멤버 전체 가져오기
//    public ResponseEntity<?> readAll(){}
}
