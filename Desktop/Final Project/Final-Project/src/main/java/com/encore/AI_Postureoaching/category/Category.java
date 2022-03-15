package com.encore.AI_Postureoaching.category;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryIdx;
    //카테고리 목록
    @Column(nullable = false)
    private String categoryValue;
    //상위 카테고리
    @Column(nullable = false)
    private String categoryParent;
    //중고거래
    private int categoryOrder;




}
