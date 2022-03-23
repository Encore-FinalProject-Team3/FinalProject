package com.encore.AI_Posturecoaching.board.controller;


import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.dto.BoardCreateRequestDto;
import com.encore.AI_Posturecoaching.board.dto.BoardReadCondition;
import com.encore.AI_Posturecoaching.board.dto.BoardUpdateRequestDto;
import com.encore.AI_Posturecoaching.board.dto.BoardUpdateResponseDto;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.board.service.BoardService;
import com.encore.AI_Posturecoaching.exception.PostNotFoundException;
import com.encore.AI_Posturecoaching.member.dto.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Board Controller", tags = "Board")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    // 게시글 목록 전체 조회
    @ApiOperation(value = "게시글 전체 목록 조회", notes = "게시글 목록을 조회한다.")
    @GetMapping("/api/board")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll() {
        return Response.success(boardService.readAll());
    }

    // 게시글 조회
    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회한다.")
    @GetMapping("/api/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@ApiParam(value = "게시글 id", required = true) @PathVariable Long id) {
        return Response.success(boardService.read(id));
    }

    // 카테고리별 게시글 조회
    @ApiOperation(value = "카테고리별 게시글 조회", notes = "카테고리별 게시글을 조회한다.")
    @GetMapping("/api/board/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response readAllByCategories(@ApiParam(value = "카테고리 id", required = true) @PathVariable Long id) {
        return Response.success(boardService.readAllByCategoryId(id));
    }

    // 게시글 생성
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    @PostMapping("/api/board")
    @ResponseStatus(HttpStatus.CREATED)
//    @AssignMemberId
    public Response create(@Valid @ModelAttribute BoardCreateRequestDto req) {
        return Response.success(boardService.create(req));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @PutMapping("/api/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(
            @ApiParam(value = "게시글 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute BoardUpdateRequestDto boardUpdateRequestDto, @AuthenticationPrincipal String memberId) {
        return Response.success(boardService.update(memberId, id, boardUpdateRequestDto));
    }

    // 게시글 삭제
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping("/api/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@ApiParam(value = "게시글 id", required = true) @PathVariable Long id, @AuthenticationPrincipal String memeberId) {
        boardService.delete(memeberId, id);
        return Response.success();
    }


}
