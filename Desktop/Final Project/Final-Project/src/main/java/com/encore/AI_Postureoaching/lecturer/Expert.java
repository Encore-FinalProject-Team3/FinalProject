package com.encore.AI_Postureoaching.lecturer;


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
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //강사번호
    private Long expertIdx;

    //강사이름
    @Column(nullable = false,length = 45)
    private String expertName;
    //경력
    @Column(nullable = false,length = 200)
    private String expertCareer;
    //강사맴버아이디
    private  Long expertMemberId;

    //일반 유저 조인
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberIdx")
    private Member member;

    //코칭 정보
    @OneToMany(mappedBy = "expert")
    private List<Coaching> coachingList =new ArrayList<>();
    //코칭 리뷰 정보
    @OneToMany(mappedBy = "expert")
    private List<Review> reviews =new ArrayList<>();
}
