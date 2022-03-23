package com.encore.AI_Posturecoaching.factory.dto;

import com.encore.AI_Posturecoaching.sign.dto.SignUpRequestDto;

public class SignUpRequestFactory {
    public static SignUpRequestDto createSignUpRequest(String email, String password,String membername) {
        return new SignUpRequestDto("member5@member5.com", "12345!", "member5");
    }

}
