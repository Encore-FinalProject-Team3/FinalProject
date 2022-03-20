package com.encore.AI_Posturecoaching.lecturer;


import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    private Long coachingIdx;
    //코칭내용
    @Lob
    @Column(nullable = false)
    private String coachingContent;
    //코칭 등록
    private int coachingRegister;
    //코칭강사
    private int coachingExpert;
    //신청일
    @CreationTimestamp
    private Timestamp coachingRequestDate;
    //응답일
    @CreationTimestamp
    private Timestamp coachingResponseDate;
    //상태
    private Boolean coachingStatus;
    //리뷰번호
    private Long reviewIdx;

    //강사 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertIdx")
    private Expert expert;
    //일반유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;

    //파일
    @OneToOne(mappedBy = "coaching")
    private File file;


}
