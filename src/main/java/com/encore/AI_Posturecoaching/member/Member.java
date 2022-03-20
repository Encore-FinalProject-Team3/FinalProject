package com.encore.AI_Posturecoaching.member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toSet;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 유저에게 고유하게 부여되는 id.

    @Column(nullable = false , name = "member_email")
    private String email;   // 아이디와 동일한 역할

    @Column(nullable = false , name="member_password")
    private String password;   // 비밀번호 , 암호화

    @Column(nullable = false ,name="member_name")
    private String memberName;

    @Column(name="member_brith")
    private Timestamp brith;  // 생일

    @Column(name="member_phone")
    private String phone;   // 핸드폰

    @Column (name = "member_gender")
    private int gender;  // 성별

    @Column(name="member_address")
    private String address; // 주소

    //권한
    @Column(name="member_role")
    private String role;

    // 회원가입시간
    @CreationTimestamp
    @Column(name="member_join_date")
    private Timestamp joinDate;

    // 수정시간
    @LastModifiedDate
    @Column(name = "member_update_date")
    private Timestamp update;

    @Column(name="member_pay_point")
    private int payPoint;

    @Column(name="member_active_point")
    private int activePoint;



    public Member(String email, String password, String memberName, String role) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.role=role;
    }

    public void updateMemberName(String memberName) {
        this.memberName = memberName;
    }


}
