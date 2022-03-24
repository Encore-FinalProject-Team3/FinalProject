package com.encore.AI_Posturecoaching.coaching.dto;


import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.coaching.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoachingResponsDto {
    //코칭 아이디
    private Long id;
    //제목
    private String title;
    //내용
    private String content;
    //상태
    private Boolean status;
    //강사 조인
    private Expert expert;
    //일반유저
    private Member member;
    //파일
    private File file;

    private String comment;


    public CoachingResponsDto(Coaching coaching) {
    }

    public CoachingResponsDto(Long id) {
    }

    public static CoachingResponsDto ToDto(Coaching coaching) {
        return new CoachingResponsDto
                (coaching.getId()
                        , coaching.getTitle()
                        , coaching.getContent()
                        , coaching.getStatus()
                        , coaching.getExpert()
                        , coaching.getMember()
                        , coaching.getFile()
                        , coaching.getComment());

    }
}
