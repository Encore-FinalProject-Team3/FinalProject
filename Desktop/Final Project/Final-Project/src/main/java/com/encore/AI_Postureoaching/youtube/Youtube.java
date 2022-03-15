package com.encore.AI_Postureoaching.youtube;

import com.encore.AI_Postureoaching.category.Category;
import com.encore.AI_Postureoaching.like.Like;
import com.encore.AI_Postureoaching.mumber.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Youtube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long youtubeIdx;
    //유튜브 주소
    @Column(nullable = false,length = 200)
    private String youtubeAddress;
    //제목
    @Column(nullable = false,length = 100)
    private String youtubeSubject;
    //조회수
    private  int  youtubeHit;
    //좋아요 수
    private int  youtubeLike;

    //좋아요
    @OneToMany(mappedBy ="youtube" )
    private List<Like> likeList =new ArrayList<>();

    //일반유저 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;
}