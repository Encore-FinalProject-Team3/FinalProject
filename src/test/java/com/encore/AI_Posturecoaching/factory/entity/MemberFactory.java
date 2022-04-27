package com.encore.AI_Posturecoaching.factory.entity;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.member.Member;
import org.springframework.test.util.ReflectionTestUtils;

import static java.util.Collections.emptyList;

public class MemberFactory {
    public static Member createMember() {
        return new Member("email1@email.com", "password1", "username1", "MEMBER");
    }
    public static Member createMember(String email,String password,String memberName, String role)
    {
        return new Member(email,password,memberName,role);
    }


}