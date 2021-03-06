package com.encore.AI_Posturecoaching.coaching.dto;


import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.expert.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
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
    private Long expert;
    //일반유저
    private Long member;
    //파일
    private File file;

    private String comment;

    public CoachingResponsDto(Coaching coaching) {
        this.id = coaching.getId();
        this.title = coaching.getTitle();
        this.content = coaching.getContent();
        this.status = coaching.getStatus();
        this.expert = coaching.getExpert().getId();
        this.member = coaching.getMember().getId();
        this.file = coaching.getFile();
        this.comment = coaching.getComment();
    }

    public CoachingResponsDto(Long id) {
    }

    public static CoachingResponsDto ToDto(Coaching coaching) {
        return new CoachingResponsDto
                (coaching.getId()
                        , coaching.getTitle()
                        , coaching.getContent()
                        , coaching.getStatus()
                        , coaching.getExpert().getId()
                        , coaching.getMember().getId()
                        , coaching.getFile()
                        , coaching.getComment());

    }
}
