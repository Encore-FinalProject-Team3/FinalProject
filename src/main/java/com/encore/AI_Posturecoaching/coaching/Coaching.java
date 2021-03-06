package com.encore.AI_Posturecoaching.coaching;

import com.encore.AI_Posturecoaching.expert.Expert;
import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.member.Member;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Coaching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coaching_id")
    private Long id;
    //코칭제목
    @Column(name = "coaching_title")
    private String title;
    //코칭내용
    @Lob
    @Column(name = "coaching_content", nullable = false)
    private String content;
    //신청일
    @CreationTimestamp
    @Column(name = "coaching_request_date")
    private Timestamp requestDate;
    //응답일
    @LastModifiedDate
    @Column(name = "coaching_response_date")
    private Timestamp responseDate;

    @Column(name = "coaching_comment")
    private  String comment;

    //상태
    @Column(name = "coaching_status")
    private Boolean status;

    //강사 조인
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    //일반유저
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //파일
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id")
    private File file;

//    public Coaching(String title,String content,Member member,Expert expert){
//        this.title = title,
//        this.content = content,
//        this.member = member;
//        this.expert = expert;
//    }
}