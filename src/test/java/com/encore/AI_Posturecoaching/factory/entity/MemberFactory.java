package com.encore.AI_Posturecoaching.factory.entity;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.member.Member;
import org.springframework.test.util.ReflectionTestUtils;

import static java.util.Collections.emptyList;

public class MemberFactory {
    public static Member createMember() {
        return new Member("email@email.com", "1234", "username", "ADMIN");
    }

    public static Member createMember2() {
        return new Member("email@email.com", "1234", "username", "MEMBER");
    }

    public static Member createMemberWithId(Long id) {
        Member member = new Member("email@email.com", "123456a!", "username", "nickname");
        ReflectionTestUtils.setField(member, "id", id);
        return member;
    }

}