package com.encore.AI_Posturecoaching.expert.dto;


import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.expert.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.*;

import java.util.List;

@Data

public class ExpertRequestDto {


    private Long id;

    private String expertName;

    private String expertCareer;

    private Long expertMemberId;

    private Member member;

    private List<Coaching> coachingList;


    public ExpertRequestDto(Expert expert) {
    }

    public ExpertRequestDto(Long id) {
    }

    public ExpertRequestDto(Long id, String expertName, String expertCareer, Long expertMemberId, Member member, List<Coaching> coachingList) {
        this.id = id;
        this.expertName = expertName;
        this.expertCareer = expertCareer;
        this.expertMemberId = expertMemberId;
        this.member = member;
        this.coachingList = coachingList;
    }

    public ExpertRequestDto(Long id, String expertName, String expertCareer, Long expertMemberId, Member member, String expertCareer1, String expertName1) {
    }

    public ExpertRequestDto(Long id, String expertName, String expertCareer, Long id1, Member member, List<Coaching> coachingList, String expertCareer1, String expertName1) {
    }

    public static ExpertRequestDto ToDto(Expert expert) {
        return new ExpertRequestDto
                (expert.getId()
                        , expert.getExpertName()
                        , expert.getExpertCareer()
                        , expert.getId()
                        , expert.getMember()
                        , expert.getCoachingList()
                        , expert.getExpertCareer()
                        , expert.getExpertName());
    }
}
