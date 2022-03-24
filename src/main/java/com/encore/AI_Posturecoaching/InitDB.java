package com.encore.AI_Posturecoaching;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.expert.Expert;
import com.encore.AI_Posturecoaching.expert.repository.ExpertRepository;
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
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDB {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final ExpertRepository expertRepository;

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
        IntStream.range(0,20)
                        .forEach(i -> memberRepository.save(
                                new Member("member"+i+"@"+"member.com"
                                        , passwordEncoder.encode("123456!"),
                                        "member"+i
                                        , "MEMBER")));

        for (int i = 21; i < 40; i++) {
            Member member = new Member("member1"+i+"@member.com"
                    , passwordEncoder.encode("123456!")
                    ,"member1"+i,"EXPERT");
            memberRepository.save(member);
            int rnd = new Random().nextInt(1000);
            Expert expert = new Expert(Long.valueOf(i+1),"용키"+rnd,"경력"+rnd,member);
            expertRepository.save(expert);
        }
    }

    private void initCategory() {
        Category c1 = categoryRepository.save(new Category("골프", null));
        Category c2 = categoryRepository.save(new Category("테니스", null));
        Category c3 = categoryRepository.save(new Category("탁구", null));
        Category c4 = categoryRepository.save(new Category("농구", null));
        Category c5 = categoryRepository.save(new Category("축구", null));
        Category c6 = categoryRepository.save(new Category("야구", null));
        Category c7 = categoryRepository.save(new Category("스퀴시", null));
        Category c8 = categoryRepository.save(new Category("중고거래", c1));
        Category c9 = categoryRepository.save(new Category("Q&A", c1));
        Category c10 = categoryRepository.save(new Category("중고거래", c2));
        Category c11 = categoryRepository.save(new Category("Q&A", c2));
        Category c12 = categoryRepository.save(new Category("중고거래", c3));
        Category c13 = categoryRepository.save(new Category("Q&A", c3));
        Category c14 = categoryRepository.save(new Category("중고거래", c4));
        Category c15 = categoryRepository.save(new Category("Q&A", c4));
        Category c16 = categoryRepository.save(new Category("중고거래", c5));
        Category c17 = categoryRepository.save(new Category("Q&A", c5));
        Category c18 = categoryRepository.save(new Category("중고거래", c6));
        Category c19 = categoryRepository.save(new Category("Q&A", c6));
        Category c20 = categoryRepository.save(new Category("중고거래", c7));
        Category c21 = categoryRepository.save(new Category("Q&A", c7));
        Category c22 = categoryRepository.save(new Category("삽니다", c8));
        Category c23 = categoryRepository.save(new Category("팝니다", c8));
        Category c24 = categoryRepository.save(new Category("질문", c9));
        Category c25 = categoryRepository.save(new Category("답변", c9));
        Category c26 = categoryRepository.save(new Category("삽니다", c10));
        Category c27 = categoryRepository.save(new Category("팝니다", c10));
        Category c28 = categoryRepository.save(new Category("질문", c11));
        Category c29 = categoryRepository.save(new Category("답변", c11));

    }

    private void initBoard() {
        List<Member> memberList = memberRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();

        for(Category c : categoryList) {
            for(Member m : memberList) {
                int rnd = new Random().nextInt(1000);
                boardRepository.save(new Board("title" + rnd,"content" + rnd,m,c,List.of()));
            }
        }


//        Member member1 = memberRepository.findAll().get(0);
//        Category category1 = categoryRepository.findAll().get(0);
//        IntStream.range(0, 10)
//                .forEach(i -> boardRepository.save(
//                        new Board("title" + i, "content" + i, member1, category1, List.of())
//                ));
//        Member member2 = memberRepository.findAll().get(1);
//        Category category2 = categoryRepository.findAll().get(1);
//        IntStream.range(0, 10)
//                .forEach(i -> boardRepository.save(
//                        new Board("title" + i, "content" + i, member2, category2, List.of())
//                ));

    }
}
