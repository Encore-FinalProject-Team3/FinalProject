package com.encore.AI_Posturecoaching.member.controller;

import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.dto.MemberUpdateRequestDto;
import com.encore.AI_Posturecoaching.member.service.MemberService;
import com.encore.AI_Posturecoaching.member.dto.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Api(value = "Member Controller", tags = "Member")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "사용자 정보 조회", notes = "사용자 정보를 조회한다.")
    @GetMapping("/api/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@ApiParam(value = "사용자 id", required = true) @PathVariable Long id) {
        return Response.success(memberService.findOne(id));
    }
    // => 자신이 멤버인경우만 데이터가 조회되야 하고 다른 사람 정보는 조회되면 안되는 문제 해결해야함


    // 사용자 전체 검색

    // 사용자 정보 수정
    @ApiOperation(value = "사용자 정보 수정", notes = "사용자 정보을 수정한다.")
    @PutMapping("/api/members/mypage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(
            @ApiParam(value = "사용자 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute MemberUpdateRequestDto memberUpdateRequestDto, @AuthenticationPrincipal String memberId ) {
        return Response.success(memberService.update(memberId, id, memberUpdateRequestDto));
    }




    @ApiOperation(value = "사용자 정보 삭제", notes = "사용자 정보를 삭제한다.")
    @DeleteMapping("/api/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@ApiParam(value = "사용자 id", required = true) @PathVariable Long id, @AuthenticationPrincipal String memberId) {
        memberService.delete(memberId, id);
        return Response.success();
    }

}
