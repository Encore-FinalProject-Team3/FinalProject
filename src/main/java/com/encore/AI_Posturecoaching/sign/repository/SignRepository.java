package com.encore.AI_Posturecoaching.sign.repository;


import com.encore.AI_Posturecoaching.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignRepository extends JpaRepository<Member,Long> {

      Optional<Member> findByEmail(String email);
      Boolean existsByEmail(String email);
      Member findByEmailAndPassword(String email, String password);

}
