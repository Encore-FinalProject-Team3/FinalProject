package com.encore.AI_Posturecoaching.member.repository;

import com.encore.AI_Posturecoaching.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findAll();
    Optional<Member> findByEmail(String email);
    Optional<Member> findByMemberName(String MemberName);

    boolean existsByEmail(String email);
    boolean existsByMemberName(String memberName);
}
