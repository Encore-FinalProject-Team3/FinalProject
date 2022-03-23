package com.encore.AI_Posturecoaching.coaching.dto;

import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.coaching.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExpertRequestDto {


    private Long id;

    private String expertName;

    private String expertCareer;

    private Long expertMemberId;

    private Member member;

    private List<Coaching> coachingList;


    @Builder
    public ExpertRequestDto(Long id) {
        this.id = id;
        this.expertName = expertName;
        this.expertCareer = expertCareer;
        this.expertMemberId = expertMemberId;
        this.member = member;
        this.coachingList = coachingList;
    }

    public ExpertRequestDto(Expert expert) {
    }

    public static ExpertRequestDto ToDto(Expert expert) {
        return new ExpertRequestDto
                (expert.getId()
                        , expert.getExpertName()
                        , expert.getExpertCareer()
                        , expert.getExpertMemberId()
                        , expert.getMember()
                        , expert.getCoachingList()
                        , expert.getExpertCareer()
                        , expert.getExpertName());
    }
}