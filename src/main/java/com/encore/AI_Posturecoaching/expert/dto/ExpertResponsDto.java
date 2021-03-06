package com.encore.AI_Posturecoaching.expert.dto;


import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.expert.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.Data;

import java.util.List;

@Data
public class ExpertResponsDto {


    private Long id;

    private String expertName;

    private String expertCareer;

    private  Long expertMemberId;

    private Member member;

    private List<Coaching> coachingList;


    public ExpertResponsDto(Expert expert) {
    }

    public ExpertResponsDto(Long id, String expertName, String expertCareer, Long expertMemberId, Member member, List<Coaching> coachingList, String expertCareer1, String expertName1) {
    }

    public ExpertResponsDto(Long id, String expertName, String expertCareer, Long expertMemberId, Member member, List<Coaching> coachingList, List<Coaching> coachingList1) {
    }

    public static ExpertResponsDto ToDto(Expert expert) {
        return new ExpertResponsDto
                (expert.getId()
                        , expert.getExpertName()
                        , expert.getExpertCareer()
                        , expert.getId()
                        , expert.getMember()
                        , expert.getCoachingList()
                        , expert.getExpertCareer()
                        , expert.getExpertName());
    }
//
//    public static ExpertResponsDto expertDTO(Expert expert, Coaching Coaching){
//
//        List<Coaching> coachings = new ArrayList<>();
//        expert.getCoachingList().forEach(x -> coachings.add(Coaching));
//        return  new  ExpertResponsDto(
//                expert.getId(),
//                expert.getExpertName(),
//                expert.getExpertCareer(),
//                expert.getExpertMemberId(),
//                expert.getMember(),
//                expert.getCoachingList(),
//                coachings
//        );
//    }

}
