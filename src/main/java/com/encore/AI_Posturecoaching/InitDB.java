package com.encore.AI_Posturecoaching;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
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
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDB {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void InitDB() {
        log.info("initialize database");

        initTestAdmin();
        initTestMember();
        initCategory();
        initBoard();
    }

    private void initTestAdmin() {
        memberRepository.save(
                new Member("ADMIN@ADMIN.com", passwordEncoder.encode("123456!"), "ADMIN", "ADMIN"));
    }

    private void initTestMember() {
        memberRepository.save(
                new Member("member1@member1.com", passwordEncoder.encode("123456!"), "member1", "MEMBER"));
        memberRepository.save(
                new Member("member2@member2.com", passwordEncoder.encode("123456!"), "member2", "MEMBER"));
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

    private void initBoard() {
        Member member1 = memberRepository.findAll().get(0);
        Category category1 = categoryRepository.findAll().get(0);
        IntStream.range(0, 10)
                .forEach(i -> boardRepository.save(
                        new Board("title" + i, "content" + i, member1, category1, List.of())
                ));
        Member member2 = memberRepository.findAll().get(1);
        Category category2 = categoryRepository.findAll().get(1);
        IntStream.range(0, 10)
                .forEach(i -> boardRepository.save(
                        new Board("title" + i, "content" + i, member2, category2, List.of())
                ));

    }
}
