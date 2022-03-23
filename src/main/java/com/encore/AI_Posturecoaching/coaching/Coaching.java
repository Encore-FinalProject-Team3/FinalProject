package com.encore.AI_Posturecoaching.coaching;

import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.member.Member;
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
public class Coaching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coaching_idx")
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
    //파일 번호
    @Column(name = "file_idx")
    private Long fileIdx;

    //상태
    @Column(name = "coaching_status")
    private Boolean status;

    //강사 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_idx")
    private Expert expert;

    //일반유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //파일
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="target_idx")
    private File file;

}