package com.encore.AI_Postureoaching.board;


import com.encore.AI_Postureoaching.mumber.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  commentIdx;

    //별명
    @Column(nullable = false,length = 20)
    private String commentMemberNickname;
    //작성일
    @CreationTimestamp
    private Timestamp commentMemberCreateDate;
    //수정일
    @CreationTimestamp
    private Timestamp commentMemberUpdateDate;
    //내용
    @Lob
    @Column(nullable = false)
    private String commentContent;

    //게시물 조인
    @ManyToOne
    @JoinColumn(name = "boardIdx")
    private Board board;

    //댓글계층
    private int commentClass;
    //댓글순서
    private int commentOrder;
    //댓글그룹
    private int commentReview;

    //회원아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;



}
