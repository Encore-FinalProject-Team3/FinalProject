package com.encore.AI_Posturecoaching.coaching.dto;

import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.coaching.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
public class ExpertResponsDto {


    private Long id;

    private String expertName;

    private String expertCareer;

    private  Long expertMemberId;

    private Member member;

    private List<Coaching> coachingList;

    @Builder
    public ExpertResponsDto(Long id, String expertName, String expertCareer, Long expertMemberId, Member member, List<Coaching> coachingList) {
        this.id = id;
        this.expertName = expertName;
        this.expertCareer = expertCareer;
        this.expertMemberId = expertMemberId;
        this.member = member;
        this.coachingList = coachingList;
    }

    public ExpertResponsDto(Expert expert) {
    }
}
