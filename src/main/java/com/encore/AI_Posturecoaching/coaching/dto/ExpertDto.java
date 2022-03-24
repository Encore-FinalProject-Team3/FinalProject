package com.encore.AI_Posturecoaching.coaching.dto;

import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.coaching.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertDto {

    private Long id;
    private String expertName;
    private String expertCareer;
    private Long memberId; // 유저에게 고유하게 부여되는 id.
    private String email;   // 아이디와 동일한 역할
    private String memberName;
    private String role;
    private List<Coaching> coachingList;

    public ExpertDto(Expert expert) {
        this.id = expert.getId();
        this.expertName = expert.getExpertName();
        this.expertCareer = expert.getExpertCareer();
        this.memberId = expert.getMember().getId();
        this.email = expert.getMember().getEmail();
        this.memberName = expert.getMember().getMemberName();
        this.role = expert.getMember().getRole();
        this.coachingList = expert.getCoachingList();
    }

    public static ExpertDto ToDto(Expert expert){
        return new ExpertDto(
                expert.getId(),
                expert.getExpertName(),
                expert.getExpertCareer(),
                expert.getMember().getId(),
                expert.getMember().getEmail(),
                expert.getMember().getMemberName(),
                expert.getMember().getRole(),
                expert.getCoachingList()

        );
    }
}
