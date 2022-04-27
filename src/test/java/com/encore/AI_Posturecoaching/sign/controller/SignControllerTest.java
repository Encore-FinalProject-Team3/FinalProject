package com.encore.AI_Posturecoaching.sign.controller;

import com.encore.AI_Posturecoaching.sign.dto.SignInRequestDto;
import com.encore.AI_Posturecoaching.sign.dto.SignInResponseDto;
import com.encore.AI_Posturecoaching.sign.dto.SignUpRequestDto;
import com.encore.AI_Posturecoaching.sign.service.SignService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SignControllerTest {
    @InjectMocks SignController signController;
    @Mock SignService signService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(signController).build();
    }

    @Test
    void signUpTest() throws Exception {
        // given
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("email@email.com","password","memberName");

        // when, then
        mockMvc.perform(
                post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequestDto)))
                .andExpect(status().isCreated());

        verify(signService).signUp(signUpRequestDto);
    }


    @Test
    void signInTest() throws Exception  {
        // given
        SignInRequestDto signInRequestDto = new SignInRequestDto("email@email.com","password");
        given(signService.signIn(signInRequestDto)).willReturn(new SignInResponseDto("access"));

        // when, then
        mockMvc.perform(
                post("/api/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.accessToken").value("access"));

        verify(signService).signIn(signInRequestDto);
    }


}