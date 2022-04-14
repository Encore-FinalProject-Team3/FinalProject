package com.encore.AI_Posturecoaching.sign.controller;
import com.encore.AI_Posturecoaching.config.jwt.TokenProvider;
import com.encore.AI_Posturecoaching.sign.dto.SignUpRequestDto;
import com.encore.AI_Posturecoaching.member.dto.response.Response;
import com.encore.AI_Posturecoaching.sign.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.encore.AI_Posturecoaching.member.dto.response.Response.success;


@Api(value = "Sign Controller", tags = "Sign")
@RequiredArgsConstructor
@RestController
public class SignController {

    private final SignService signService;

    private final TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // test home
    @GetMapping
    public String home(){
        return "hello world4";
    }

    // 회원가입
    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @PostMapping("/api/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        signService.signUp(signUpRequestDto);
        return success();
    }

    @ApiOperation(value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/api/signin")
    @ResponseStatus(HttpStatus.OK)
    public Response authenticate(@RequestBody SignUpRequestDto signUpRequestDto) {
        return success(signService.signIn(signUpRequestDto));
    }

    // 이메일 체크
    @ApiOperation(value = "이메일 체크", notes = "이메일이 등록 되어 있는지 확인 한다.")
    @PostMapping("/api/confirm")
    @ResponseStatus(HttpStatus.CREATED)
    public Response confirm(@RequestBody SignUpRequestDto signUpRequestDto) {
        signService.confirm(signUpRequestDto);
        return success();
    }

}
