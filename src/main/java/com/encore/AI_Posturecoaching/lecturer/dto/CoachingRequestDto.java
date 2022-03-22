package com.encore.AI_Posturecoaching.lecturer.dto;


import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.lecturer.Coaching;
import com.encore.AI_Posturecoaching.lecturer.Expert;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoachingRequestDto {
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


    @Builder
    public CoachingRequestDto(Long id, String title, String content, Boolean status, Expert expert, Member member, File file, String comment) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.expert = expert;
        this.member = member;
        this.file = file;
    }

    public static CoachingRequestDto ToDto(Coaching coaching) {
        return new CoachingRequestDto
                (coaching.getId()
                ,coaching.getTitle()
                ,coaching.getContent()
                ,coaching.getStatus()
                ,coaching.getExpert()
                ,coaching.getMember()
                ,coaching.getFile());

    }
}