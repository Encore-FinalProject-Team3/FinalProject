package com.encore.AI_Posturecoaching.lecturer.controller;


import com.encore.AI_Posturecoaching.lecturer.dto.CoachingRequestDto;
import com.encore.AI_Posturecoaching.lecturer.service.CoachingService;
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
public class CoachingController {

    private final CoachingService coachingService;


    // 코칭 한건 조회
    @ApiOperation(value = "코칭 정보 조회", notes = "코칭 정보를 조회한다.")
    @GetMapping("/api/coaching/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@ApiParam(value = "사용자 id", required = true) @PathVariable Long id) {
        return Response.success(coachingService.findOne(id));

    }
   //코칭 목록 모두 보기
    @ApiOperation(value = "코칭 전체 목록 조회", notes = "코칭 목록을 조회한다.")
    @GetMapping("/api/coaching")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll() {
        return Response.success(coachingService.findList());
    }
    //코칭 수정
    @ApiOperation(value = "코칭 수정", notes = "코칭을 수정한다.")
    @PutMapping("/api/coaching/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(
            @ApiParam(value = "코칭 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute CoachingRequestDto coachingRequestDto, @AuthenticationPrincipal String memberId) {
        return Response.success(coachingService.update(memberId, id, coachingRequestDto));
    }

}