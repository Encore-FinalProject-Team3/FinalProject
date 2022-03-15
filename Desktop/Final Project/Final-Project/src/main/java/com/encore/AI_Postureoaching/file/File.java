package com.encore.AI_Postureoaching.file;


import com.encore.AI_Postureoaching.board.Board;
import com.encore.AI_Postureoaching.lecturer.Coaching;
import com.encore.AI_Postureoaching.mumber.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
