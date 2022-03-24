package com.encore.AI_Posturecoaching.coaching.service;


import com.encore.AI_Posturecoaching.coaching.Expert;
import com.encore.AI_Posturecoaching.coaching.dto.ExpertDto;
import com.encore.AI_Posturecoaching.coaching.dto.ExpertRequestDto;
import com.encore.AI_Posturecoaching.coaching.repository.ExpertRepository;
import com.encore.AI_Posturecoaching.exception.ExpertNotFoundException;
import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExpertService {


    private final ExpertRepository expertRepository;
    private final MemberRepository memberRepository;


    //강사 신청
    @Transactional
    public void create(ExpertRequestDto requestDto) {
        expertRepository.save(Expert.builder()
                .expertCareer(requestDto.getExpertCareer())
                .expertName(requestDto.getExpertName())
                .member(requestDto.getMember())
                .coachingList(requestDto.getCoachingList())
                .build());
    }

    //전체 조회
    @Transactional
    public List<ExpertDto> findAll() {
        List<ExpertDto> expertDtos = expertRepository.findAll().stream().map(ExpertDto::new).collect(Collectors.toList());
        return expertDtos;
    }

    //강사 한건 가져오기
    @Transactional(readOnly = true)
    public ExpertDto findOne(Long id) {
        ExpertDto expertDto = ExpertDto.ToDto(expertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "는 존재하지 않습니다.")));
        return expertDto;
    }

    //강사 수정
    @Transactional
    public void update(String memberId, Long id, ExpertRequestDto expertRequestDto) {
        Member useMember = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new);
        if (useMember.getRole().equals("EXPERT") || useMember.getRole().equals("ADMIN")) {
            final Optional<Expert> original = expertRepository.findById(id);
            original.ifPresent(newData -> {
                newData.setExpertName(expertRequestDto.getExpertName());
                newData.setExpertCareer(expertRequestDto.getExpertCareer());
                expertRepository.save(newData);
            });
        } else {
            throw new RuntimeException("관리자 또는 자신의 정보만 수정 가능합니다");
        }
    }

    //강사 탈퇴
    @Transactional
    public void delete(String memberId, Long id) {
        String role = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(ExpertNotFoundException::new).getRole();
        if (role.equals("EXPERT")) {
            Expert deleteMember = expertRepository.findById(id).orElseThrow(ExpertNotFoundException::new);
            expertRepository.delete(deleteMember);
        } else {
            throw new RuntimeException("강사만 삭제할 수 있습니다");
        }
    }


}
