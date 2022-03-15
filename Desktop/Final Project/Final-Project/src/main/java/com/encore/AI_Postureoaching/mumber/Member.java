package com.encore.AI_Postureoaching.mumber;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;

    //이름
    @Column(nullable = false , length = 30)
    private String name;
    //비밀번호
    @Column(nullable = false , length = 50)
    private String password;
    //별명
    @Column(nullable = false ,length = 30)
    private String nicName;
    //생일
    @Column(nullable = false ,length = 20)
    private Timestamp memberBrith;
    //핸드폰
    @Column(nullable = false ,length = 15)
    private String memberPhone;
    //성별
    @Column(nullable = false ,length = 1)
    private int memberGender;

    //유저주소
    @Column(nullable = false ,length = 50)
    private String memberAddress;

    //프로필
    private int memberImage;

    //회원가입시간
    @CreationTimestamp
    private Timestamp joinDate;

    //수정시간
    @CreationTimestamp
    private Timestamp leaveDate;

    @ColumnDefault("0")
    private int member_pay_point;

    @Enumerated(EnumType.STRING)
    private RoleType Role;


}
