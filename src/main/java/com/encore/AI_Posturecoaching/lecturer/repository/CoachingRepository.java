package com.encore.AI_Posturecoaching.lecturer.repository;

import com.encore.AI_Posturecoaching.lecturer.Coaching;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachingRepository extends JpaRepository<Coaching,Long> {
    //코칭 단건 조회
    Optional<Coaching> findById (Long id);
    //강사별 코칭 신청 리스트
    List<Coaching> findAllByExpertId (Long id);
    //전체 리스트
    List<Coaching> findAll ();
}
