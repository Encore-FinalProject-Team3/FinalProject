package com.encore.AI_Postureoaching.mumber.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class MemberDto {
    private Long memberIdx;
    private String name;

    private String password;

    private String nicName;

    private Timestamp memberBrith;

    private String memberPhone;

    private int memberGender;

    private String memberAddress;

    private int memberImage;

    @CreationTimestamp
    private Timestamp joinDate;

    @CreationTimestamp
    private Timestamp leaveDate;

    private int member_pay_point;


}
