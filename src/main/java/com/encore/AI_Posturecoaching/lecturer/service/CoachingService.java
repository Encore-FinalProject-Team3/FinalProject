package com.encore.AI_Posturecoaching.lecturer.service;


import com.encore.AI_Posturecoaching.exception.CoachingNotFoundException;
import com.encore.AI_Posturecoaching.lecturer.Coaching;
import com.encore.AI_Posturecoaching.lecturer.dto.CoachingResponsDto;
import com.encore.AI_Posturecoaching.lecturer.dto.CoachingRequestDto;
import com.encore.AI_Posturecoaching.lecturer.repository.CoachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachingService {

    private final CoachingRepository coachingRepository;

    //강의 신청 (List)
    public List<CoachingResponsDto> findList(Long id){
         List<CoachingResponsDto> coachingAll = coachingRepository.findAll().stream()
                .map(CoachingResponsDto::new).collect(Collectors.toList());
          return coachingAll;
    }


    //강의신청 (get)
    public CoachingRequestDto findOne(Long id) {
        return CoachingRequestDto.ToDto(coachingRepository.findById(id).orElseThrow(CoachingNotFoundException::new));
    }

    //수정
}
