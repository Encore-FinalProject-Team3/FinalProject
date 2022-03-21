package com.encore.AI_Posturecoaching;

import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDB {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void InitDB() {
        log.info("initialize database");

        initTestAdmin();
        initTestMember();
        initCategory();



    }

    private void initTestAdmin() {
        memberRepository.save(
                new Member("ADMIN@ADMIN.com", passwordEncoder.encode("1234"), "ADMIN", "ADMIN"));
    }

    private void initTestMember() {
        memberRepository.save(
                new Member("member1@member2.com", passwordEncoder.encode("1234"), "member1", "MEMBER"));
        memberRepository.save(
                new Member("member2@member2.com", passwordEncoder.encode("1234"), "member2", "MEMBER"));
    }

    private void initCategory() {
        Category c1 = categoryRepository.save(new Category("골프", null));
        Category c2 = categoryRepository.save(new Category("중고거래", c1));
        Category c3 = categoryRepository.save(new Category("Q&A", c1));
        Category c4 = categoryRepository.save(new Category("삽니다", c2));
        Category c5 = categoryRepository.save(new Category("팝니다", c2));
        Category c6 = categoryRepository.save(new Category("질문", c3));
        Category c7 = categoryRepository.save(new Category("대답", c3));
        Category c8 = categoryRepository.save(new Category("테니스", null));
    }
}
