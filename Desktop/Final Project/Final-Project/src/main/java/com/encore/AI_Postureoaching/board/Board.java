package com.encore.AI_Postureoaching.board;


import com.encore.AI_Postureoaching.category.Category;
import com.encore.AI_Postureoaching.file.File;
import com.encore.AI_Postureoaching.like.Like;
import com.encore.AI_Postureoaching.mumber.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //게시글 번호
    private Long boardIdx;
   @Column(nullable = false,length = 45)
    //제목
    private String boardTitle;
    @Column(nullable = false,length = 45)
    //작성자
    private int boardWriter;
    //작성자 별명
    @Column(nullable = false,length = 45)
    private String boardWriterNickname;
    //작성일
    @CreationTimestamp
    private Timestamp boardCreateDate;
    //내용
    @Lob
    @Column(nullable = false)
    private String boardContent;
    //수정일
    @CreationTimestamp
    private Timestamp boardUpdateDate;
    //조회수
    private int boardHit;
   //이미지
    private int image;

    //일반유저 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;
    //댓글
    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    //좋아요
    @OneToMany(mappedBy = "board" )
    private List<Like> likes = new ArrayList<>();
    //중고 상품 등록 파일
    @OneToMany(mappedBy = "board")
    private List<File> files =new ArrayList<>();

    
}
