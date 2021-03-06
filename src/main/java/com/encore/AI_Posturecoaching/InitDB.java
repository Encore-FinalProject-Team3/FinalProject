package com.encore.AI_Posturecoaching;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.coaching.Coaching;
import com.encore.AI_Posturecoaching.coaching.repository.CoachingRepository;
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

import java.util.ArrayList;
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
    private final CoachingRepository coachingRepository;

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
            Expert expert = new Expert(Long.valueOf(i+1),"??????"+rnd,"??????"+rnd,member);
            expertRepository.save(expert);
        }
    }

    private void initCategory() {
        Category c1 = categoryRepository.save(new Category("??????", null));
        Category c2 = categoryRepository.save(new Category("?????????", null));
        Category c3 = categoryRepository.save(new Category("??????", null));
        Category c4 = categoryRepository.save(new Category("??????", null));
        Category c5 = categoryRepository.save(new Category("??????", null));
        Category c6 = categoryRepository.save(new Category("??????", null));
        Category c7 = categoryRepository.save(new Category("?????????", null));
        Category c8 = categoryRepository.save(new Category("????????????", c1));
        Category c9 = categoryRepository.save(new Category("Q&A", c1));
        Category c10 = categoryRepository.save(new Category("????????????", c2));
        Category c11 = categoryRepository.save(new Category("Q&A", c2));
        Category c12 = categoryRepository.save(new Category("????????????", c3));
        Category c13 = categoryRepository.save(new Category("Q&A", c3));
        Category c14 = categoryRepository.save(new Category("????????????", c4));
        Category c15 = categoryRepository.save(new Category("Q&A", c4));
        Category c16 = categoryRepository.save(new Category("????????????", c5));
        Category c17 = categoryRepository.save(new Category("Q&A", c5));
        Category c18 = categoryRepository.save(new Category("????????????", c6));
        Category c19 = categoryRepository.save(new Category("Q&A", c6));
        Category c20 = categoryRepository.save(new Category("????????????", c7));
        Category c21 = categoryRepository.save(new Category("Q&A", c7));
        Category c22 = categoryRepository.save(new Category("?????????", c8));
        Category c23 = categoryRepository.save(new Category("?????????", c8));
        Category c24 = categoryRepository.save(new Category("??????", c9));
        Category c25 = categoryRepository.save(new Category("??????", c9));
        Category c26 = categoryRepository.save(new Category("?????????", c10));
        Category c27 = categoryRepository.save(new Category("?????????", c10));
        Category c28 = categoryRepository.save(new Category("??????", c11));
        Category c29 = categoryRepository.save(new Category("??????", c11));

    }

    private void initBoard() {
        List<Member> memberList = memberRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();

        for(Category c : categoryList) {
            for(Member m : memberList) {
                int rnd = new Random().nextInt(9);
                List<String> titleList = new ArrayList<>();
                titleList.add("?????? ??????????????? ??????????????? 273g/105sq ??????");
                titleList.add("????????????)2022???????????? 285 ??????");
                titleList.add("????????? 3??? 26??? ??????7???~10??? GTA(?????????) ????????? ??????");
                titleList.add("??????????????? ?????? ??????(????????? ??????)");
                titleList.add("???????????????????????????^^");
                titleList.add("???????????? ????????????!");
                titleList.add("[Q ??? A] ???????????? ??????????????????. [4] new");
                titleList.add("???????????? ??????(???????????? ??????????????? ???????????????)");
                titleList.add("?????? ?????? ?????? ?????? ????????????~ [14]");

                boardRepository.save(new Board(titleList.get(rnd), titleList.get(rnd),m,c,List.of()));
            }
        }
    }

    private void initCoaching() {
        Member member = memberRepository.findAll().get(0);
        Expert expert = expertRepository.findAll().get(0);
//        coachingRepository.save(new Coaching("title"))


    }


}
