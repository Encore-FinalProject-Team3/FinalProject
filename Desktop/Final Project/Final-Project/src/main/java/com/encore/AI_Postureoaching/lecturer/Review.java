package com.encore.AI_Postureoaching.lecturer;


import com.encore.AI_Postureoaching.mumber.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewIdx;

    private Long reviewMemberId;
    //별명
    @Column(nullable = false,length = 50)
    private String reviewMemberNickname;
    //평점
    private int reviewScore;
    //강사번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertId")
    private  Expert expert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;


}
