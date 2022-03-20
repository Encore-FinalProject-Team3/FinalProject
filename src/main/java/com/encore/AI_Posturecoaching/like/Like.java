package com.encore.AI_Posturecoaching.like;



import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.youtube.Youtube;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long targetId;

    //일반유저 게시글 좋아요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;
    // 일반유저 유튜브 좋아요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtubeIdx")
    private Youtube youtube;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardIdx")
    private Board board;


    //좋아요 대상
   private  int like_something;
   //좋아요
   private boolean status;
   
   //등록 날짜
    @CreationTimestamp
    private Timestamp like_date;
}
