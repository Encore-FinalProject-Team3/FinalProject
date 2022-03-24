package com.encore.AI_Posturecoaching.expert.dto;

import com.encore.AI_Posturecoaching.expert.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class ExpertDto {

    private Long id;

    private String expertName;

    private String expertCareer;

    private Long expertMemberId;

    private Member member;

    @Builder
    public ExpertDto (Long id, String expertName, String expertCareer, Long expertMemberId, Member member) {
        this.id = id;
        this.expertName = expertName;
        this.expertCareer = expertCareer;
        this.expertMemberId = expertMemberId;
        this.member = member;
    }

    public ExpertDto(Expert expert) {
    }

}
