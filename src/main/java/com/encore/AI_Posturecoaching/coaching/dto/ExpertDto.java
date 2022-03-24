package com.encore.AI_Posturecoaching.coaching.dto;

import com.encore.AI_Posturecoaching.coaching.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class ExpertDto {

    private Long id;

    private String expertName;

    private String expertCareer;

    private Long expertMemberId;

    @Builder
    public ExpertDto (Long id, String expertName, String expertCareer, Long expertMemberId, Member member) {
        this.id = id;
        this.expertName = expertName;
        this.expertCareer = expertCareer;
        this.expertMemberId = expertMemberId;
    }

    public ExpertDto(Expert expert) {
    }

    public ExpertDto(ExpertDto e) {
    }
}
