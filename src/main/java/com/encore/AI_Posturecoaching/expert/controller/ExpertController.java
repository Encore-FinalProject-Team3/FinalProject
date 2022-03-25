package com.encore.AI_Posturecoaching.expert.controller;


import com.encore.AI_Posturecoaching.expert.dto.ExpertRequestDto;
import com.encore.AI_Posturecoaching.expert.service.ExpertService;
import com.encore.AI_Posturecoaching.member.dto.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertService expertService;

    // 강사 조회
    @ApiOperation(value = "강사 정보 조회", notes = "강사 정보를 조회한다.")
    @GetMapping("/api/expert/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@ApiParam(value = "강사 id", required = true) @PathVariable Long id) {
        return Response.success(expertService.findOne(id));
    }

    //강사 모두 출력 하기
    @ApiOperation(value = "강사 전체 목록 조회", notes = "강사 목록을 조회한다.")
    @GetMapping("/api/expert")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll() {
        return Response.success(expertService.findAll());
    }

    //강사 수정
    @ApiOperation(value = "강사 수정", notes = "강사 을 수정한다.")
    @PutMapping("/api/expert/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(
            @ApiParam(value = "강사 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute ExpertRequestDto expertRequestDto, @AuthenticationPrincipal String memberId) {
        return Response.success(expertService.update(memberId, id, expertRequestDto));
    }

    // 강사 탈퇴
    @ApiOperation(value = "사용자 정보 삭제", notes = "사용자 정보를 삭제한다.")
    @DeleteMapping("/api/expert/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@ApiParam(value = "사용자 id", required = true) @PathVariable Long id, @AuthenticationPrincipal String memberId) {
        expertService.delete(memberId, id);
        return Response.success();
    }
}
