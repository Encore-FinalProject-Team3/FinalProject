package com.encore.AI_Posturecoaching.member.controller;

import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import com.encore.AI_Posturecoaching.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @InjectMocks MemberController memberController;
    @Mock MemberService memberService;
    @Mock MemberRepository memberRepository;
    MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void findOneTest() throws Exception {
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                get("/api/members/{id}",id))
                .andExpect(status().isOk());

        verify(memberService).findOne(id);
    }


    void deleteTest() throws Exception {
        // given
        Member member = createMember("ADMIN@ADMIN","password","ADMIN","ADMIN");



        // when, then
//        mockMvc.perform(
//                delete("/api/members/{id}", id))
//                .andExpect(status().isOk());
//        verify(memberService).delete("1",id);
    }


}