package com.encore.AI_Posturecoaching.factory.dto;

import com.encore.AI_Posturecoaching.sign.dto.SignUpRequestDto;

public class SignUpRequestFactory {
    public static SignUpRequestDto createSignUpRequestDto() {
        return new SignUpRequestDto("memberTest@member.com", "password1", "member1");
    }

}
