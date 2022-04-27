package com.encore.AI_Posturecoaching.member.repository;

import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;


    @Test
    void findAll() {
    }

    @Test
    void findByEmailTest() {
        // given
        Member member = memberRepository.save(createMember());
        clear();

        // when
        Member foundMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(MemberNotFoundException::new);

        // then
        assertThat(foundMember.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    void findByMemberNameTest() {
        // given
        Member member = memberRepository.save(createMember());
        clear();

        // when
        Member foundMember = memberRepository.findByMemberName(member.getMemberName()).orElseThrow(MemberNotFoundException::new);

        // then
        assertThat(foundMember.getMemberName()).isEqualTo(member.getMemberName());
    }

    @Test
    void uniqueEmailTest(){
        // given
        Member member1 = memberRepository.save(createMember("email1","password1","memberName1","member"));
        clear();

        // when
        assertThatThrownBy(() ->memberRepository.save(createMember(member1.getEmail(),"password2","memberName2","MEMBER")))
                .isInstanceOf(DataIntegrityViolationException.class);
        // then
    }

    @Test
    void uniqueMemberNameTest(){
        // given
        Member member1 = memberRepository.save(createMember("email1","password1","memberName1","member"));
        clear();

        // when, then
        assertThatThrownBy(() ->memberRepository.save(createMember("email2","password2",member1.getMemberName(),"MEMBER")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }



    @Test
    void existsByEmailTest() {
        // given
        Member member = memberRepository.save(createMember());
        clear();

        // when, then
        assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();
        assertThat(memberRepository.existsByEmail(member.getEmail()+"test")).isFalse();
    }

    public void clear(){
        em.flush();
        em.clear();
    }

}