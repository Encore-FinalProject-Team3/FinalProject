package com.encore.AI_Postureoaching.mumber.repository;

import com.encore.AI_Postureoaching.mumber.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByName(String name);
}
