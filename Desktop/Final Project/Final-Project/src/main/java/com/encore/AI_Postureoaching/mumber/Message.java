package com.encore.AI_Postureoaching.mumber;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageIdx;
    //보내는사람
    @Column(nullable = false)
    private  int messageSender;
    //받는사람
    @Column(nullable = false)
    private int messageReceiver;
    //제목
    @Column(nullable = false,length = 100)
    private  String messageTitle;
    //내용
    @Column(nullable = false)
    @Lob
    private  String messageContent;
    //보낸시간
    @CreationTimestamp
    private  Timestamp messageSendTime;
    //확인시간
    @CreationTimestamp
    private Timestamp messageReceiveTime;
    //확인여부
    @Column
    private String messageReceiveCheck;
    //일반유저 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;
}
