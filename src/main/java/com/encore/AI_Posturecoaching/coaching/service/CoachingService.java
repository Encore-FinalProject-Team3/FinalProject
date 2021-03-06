package com.encore.AI_Posturecoaching.coaching.service;


import com.encore.AI_Posturecoaching.coaching.dto.CoachingExpertResponsDto;
import com.encore.AI_Posturecoaching.exception.CoachingNotFoundException;
import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.coaching.dto.CoachingResponsDto;
import com.encore.AI_Posturecoaching.coaching.dto.CoachingRequestDto;
import com.encore.AI_Posturecoaching.coaching.repository.CoachingRepository;
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
public class CoachingService {

    private final CoachingRepository coachingRepository;
    private final MemberRepository memberRepository;

    //코칭 목록 (List)
    @Transactional
    public List<CoachingResponsDto> findList() {
        List<CoachingResponsDto> coachingAll = coachingRepository.findAll().stream()
                .map(CoachingResponsDto::new).collect(Collectors.toList());
        return coachingAll;
    }

    //코칭 한건 (get)
    @Transactional
    public CoachingRequestDto findOne(Long id) {
        return CoachingRequestDto.ToDto(coachingRepository.findById(id).orElseThrow(CoachingNotFoundException::new));
    }
    //코칭 수정
    @Transactional
    public CoachingResponsDto update(String memberId, Long id, CoachingRequestDto coachingRequestDto) {
        Member useMember = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new);
        if (useMember.getRole().equals("EXPERT") || useMember.getRole().equals("ADMIN")) {
            final Optional<Coaching> original = coachingRepository.findById(id);
            original.ifPresent(newData -> {
                newData.setStatus(true);
                newData.setComment(coachingRequestDto.getComment());

                coachingRepository.save(newData);
            });
        } else {
            throw new RuntimeException("관리자 또는 강사님만 수정 가능합니다");
        }
        return new CoachingResponsDto(useMember.getId());
    }

    // 코칭 신청
    @Transactional
    public void create(CoachingRequestDto coachingRequestDto) {
//        Member member = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(CoachingNotFoundException::new);
        coachingRepository.save(Coaching.builder()
                .title(coachingRequestDto.getTitle())
                .content(coachingRequestDto.getContent())
                .status(false)
                .expert(coachingRequestDto.getExpert())
                .member(coachingRequestDto.getMember()).build());
    }


    @Transactional
    public List<CoachingResponsDto> readAllByMember(Long id) {
        List<CoachingResponsDto> coachingAll = coachingRepository.findAllByMemberId(id).stream()
                .map(CoachingResponsDto::new).collect(Collectors.toList());
        return coachingAll;
    }

    @Transactional
    public List<CoachingExpertResponsDto> readAllByExpert(Long id) {
        List<CoachingExpertResponsDto> coachingAll = coachingRepository.findAllByExpertId(id).stream()
                .map(CoachingExpertResponsDto::new).collect(Collectors.toList());
        return coachingAll;
    }

}
