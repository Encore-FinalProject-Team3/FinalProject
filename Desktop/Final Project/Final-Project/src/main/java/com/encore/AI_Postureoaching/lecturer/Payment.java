package com.encore.AI_Postureoaching.lecturer;

import com.encore.AI_Postureoaching.mumber.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payIdx;
    //신청일
    @CreationTimestamp
    private Timestamp pay_request_date;
    //환불
    private Boolean pay_refund_check;
    //결제상태
    private Boolean pay_status;
    //포인트
    private int point;

    //결제멤버
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;

}
