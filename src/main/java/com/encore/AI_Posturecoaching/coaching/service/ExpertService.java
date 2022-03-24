package com.encore.AI_Posturecoaching.coaching.service;


import com.encore.AI_Posturecoaching.coaching.Expert;
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

    @Transactional
    public List<ExpertRequestDto> findAll() {
        List<ExpertRequestDto> expertAll = expertRepository.findAll().stream()
                .map(ExpertRequestDto::new).collect(Collectors.toList());
        return expertAll;
    }

    //강사 한건 가져오기
    @Transactional
    public ExpertRequestDto findOne(Long id) {

        return ExpertRequestDto.ToDto(expertRepository.findById(id).orElseThrow(ExpertNotFoundException::new));

    }

    //강사 수정
    @Transactional
    public ExpertRequestDto update(String memberId, Long id, ExpertRequestDto expertRequestDto) {
        Member useMember = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new);
        if (useMember.getRole().equals("MANAGER") || useMember.getRole().equals("ADMIN")) {
            final Optional<Expert> original = expertRepository.findById(id);
            original.ifPresent(newData -> {
                newData.setExpertName(expertRequestDto.getExpertName());
                newData.setId(expertRequestDto.getId());
                expertRepository.save(newData);
            });
        } else {
            throw new RuntimeException("관리자 또는 자신의 정보만 수정 가능합니다");
        }
        return new ExpertRequestDto(useMember.getId());
    }

    //강사 탈퇴
    @Transactional
    public void delete(String memberId, Long id) {
        String role = expertRepository.findById(Long.valueOf(id)).orElseThrow(MemberNotFoundException::new).getMember().getRole();
        if (role.equals("MANAGER")) {
            Expert deleteExpert = expertRepository.findById(Long.valueOf(id)).orElseThrow(MemberNotFoundException::new);
            expertRepository.delete(deleteExpert);
        } else {
            throw new RuntimeException("강사만 삭제할 수 있습니다");
        }
    }



}
