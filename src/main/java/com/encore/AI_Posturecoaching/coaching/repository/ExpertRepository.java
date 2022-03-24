package com.encore.AI_Posturecoaching.coaching.repository;


import com.encore.AI_Posturecoaching.coaching.Expert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {

    //강사 아이디 한건 찾기
    //강사 이름 모두 찾기


}
