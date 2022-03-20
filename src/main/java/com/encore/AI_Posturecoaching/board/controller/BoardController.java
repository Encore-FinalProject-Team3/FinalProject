package com.encore.AI_Posturecoaching.board.controller;


import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.dto.BoardDto;
import com.encore.AI_Posturecoaching.board.dto.BoardReadCondition;
import com.encore.AI_Posturecoaching.board.service.BoardService;
import com.encore.AI_Posturecoaching.member.dto.ResponseDto;
import com.encore.AI_Posturecoaching.member.dto.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Board Controller", tags = "Board")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 게시글 전체 목록 조회
//    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회한다.")
//    @GetMapping("/api/posts")
//    @ResponseStatus(HttpStatus.OK)
//    public Response readAll(@Valid BoardReadCondition cond) {
//        return Response.success(boardService.readAll(cond));
//    }

    // 게시글 목록 조회

    // 게시글 생성

    // 게시글 삭제

    // 게시글 수정






    /*@PostMapping
    public ResponseEntity<?> createBoard(
            @AuthenticationPrincipal String memberId,
            @RequestBody BoardDto dto) {
        try {
            System.out.println("게시글 생성");
            // (1) Board로 변환한다.
            Board entity = BoardDto.toEntity(dto);

            // (2) id를 null로 초기화 한다. 생성 당시에는 id가 없어야 하기 때문이다.
            entity.setId(null);

            // (3) 임시 유저 아이디를 설정 해 준다. 이 부분은 4장 인증과 인가에서 수정 할 예정이다. 지금은 인증과 인가 기능이 없으므로 한 유저(temporary-user)만 로그인 없이 사용 가능한 어플리케이션인 셈이다
            entity.setMemberId(memberId);

            // (4) 서비스를 이용해 Todo엔티티를 생성한다.
            List<Board> entities = boardService.create(entity);

            // (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardDto리스트로 변환한다.

            List<BoardDto> dtos = entities.stream().map(BoardDto::new).collect(Collectors.toList());

            // (6) 변환된 BoardDto리스트를 이용해 ResponseDto를 초기화한다.
            ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().data(dtos).build();

            // (7) ResponseDTO를 리턴한다.
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.

            String error = e.getMessage();
            ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveBoardList(
            @AuthenticationPrincipal String memberId) {
        System.out.println("게시글 검색");
        // (1) 서비스 메서드의 retrieve메서드를 사용해 Todo리스트를 가져온다
        List<Board> entities = boardService.retrieve(memberId);

        // (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
        List<BoardDto> dtos = entities.stream().map(BoardDto::new).collect(Collectors.toList());

        // (6) 변환된 TodoDTO리스트를 이용해ResponseDTO를 초기화한다.
        ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().data(dtos).build();

        // (7) ResponseDTO를 리턴한다.
        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId,
                                        @RequestBody BoardDto dto) {
        // (1) dto를 entity로 변환한다.
        System.out.println("게시글 수정");
        Board entity = BoardDto.toEntity(dto);

        // (2) id를 userId 초기화 한다.
        entity.setMemberId(userId);

        // (3) 서비스를 이용해 entity를 업데이트 한다.
        List<Board> entities = boardService.update(entity);

        // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
        List<BoardDto> dtos = entities.stream().map(BoardDto::new).collect(Collectors.toList());

        // (5) 변환된 TodoDTO리스트를 이용해ResponseDTO를 초기화한다.
        ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().data(dtos).build();

        // (6) ResponseDTO를 리턴한다.
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(
            @AuthenticationPrincipal String userId,
            @RequestBody BoardDto dto) {
        try {
            // (1) TodoEntity로 변환한다.
            System.out.println("게시글 삭제");
            Board entity = BoardDto.toEntity(dto);

            // (2) 임시 유저 아이디를 설정 해 준다.
            entity.setMemberId(userId);

            // (3) 서비스를 이용해 entity를 삭제 한다.
            List<Board> entities = boardService.delete(entity);

            // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
            List<BoardDto> dtos = entities.stream().map(BoardDto::new).collect(Collectors.toList());

            // (5) 변환된 TodoDTO리스트를 이용해ResponseDTO를 초기화한다.
            ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().data(dtos).build();

            // (6) ResponseDTO를 리턴한다.
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }*/
}
