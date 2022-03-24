package com.encore.AI_Posturecoaching.expert.repository;


import com.encore.AI_Posturecoaching.expert.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    //강사 아이디 한건 찾기
    //강사 이름 모두 찾기


}
