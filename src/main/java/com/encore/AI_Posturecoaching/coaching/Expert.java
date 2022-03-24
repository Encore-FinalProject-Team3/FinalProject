package com.encore.AI_Posturecoaching.coaching;



import com.encore.AI_Posturecoaching.member.Member;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expert_id")
    //강사번호
    private Long id;

    //강사이름
    @Column(nullable = false,length = 45)
    private String expertName;

    //경력
    @Column(nullable = false,length = 200)
    private String expertCareer;

    //일반 유저 조인
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_Id")
    private Member member;

    //코칭 정보
    @OneToMany(mappedBy = "expert")
    private List<Coaching> coachingList;



}
