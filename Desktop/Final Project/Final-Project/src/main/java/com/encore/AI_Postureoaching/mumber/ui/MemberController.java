package com.encore.AI_Postureoaching.mumber.ui;


import com.encore.AI_Postureoaching.mumber.Member;
import com.encore.AI_Postureoaching.mumber.dto.MemberDto;
import com.encore.AI_Postureoaching.mumber.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final HttpSession httpSession;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDto member){
        memberService.join(member);
        return new ResponseEntity<String>("ok",HttpStatus.CREATED);
    }
    //로그인
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto member){
        memberService.login(member.getName());
        return new ResponseEntity<String>("ok",HttpStatus.OK);
    }
    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        httpSession.invalidate();
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

}
