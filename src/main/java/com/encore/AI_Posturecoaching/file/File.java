package com.encore.AI_Posturecoaching.file;


import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.lecturer.Coaching;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class File {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileIdx;
    //분류
    private int fileClassification;
    //데이터값
    @Lob
    @Column(nullable = false)
    private String fileLocation;
    //파일이름
    @Column(nullable = false,length = 45)
    private String fileName;

    //일반유저 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("memberIdx"))
    private Member member;

    //게시물 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardIdx")
    private Board board;

    @OneToOne(cascade = CascadeType.ALL)
    private Coaching coaching;
}
