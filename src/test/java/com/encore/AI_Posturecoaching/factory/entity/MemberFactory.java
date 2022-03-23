package com.encore.AI_Posturecoaching.factory.entity;

import com.encore.AI_Posturecoaching.member.Member;

import static java.util.Collections.emptyList;

public class MemberFactory {
    public static Member createMember() {
        return new Member("email@email.com", "1234", "username", "ADMIN");
    }
    public static Member createMember2() {
        return new Member("email@email.com", "1234", "username", "MEMBER");
    }

}
