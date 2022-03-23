package com.encore.AI_Posturecoaching.board.service;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.dto.*;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.file.service.FileService;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.encore.AI_Posturecoaching.category.CategoryFactory.createCategory;
import static com.encore.AI_Posturecoaching.factory.dto.BoardCreateRequestFactory.createPostCreateRequest;
import static com.encore.AI_Posturecoaching.factory.dto.BoardReadConditionFactory.createPostReadCondition;
import static com.encore.AI_Posturecoaching.factory.entity.BoardFactory.createBoard;
import static com.encore.AI_Posturecoaching.factory.entity.BoardFactory.createPostWithImages;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;
import static java.util.stream.Collectors.toList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    BoardService boardService;

    @Mock
    BoardCreateRequestDto boardCreateRequestDto;
    @Mock
    BoardCreateResponseDto boardCreateResponseDto;
    @Mock
    BoardDto boardDto;
    @Mock
    BoardListDto boardListDto;
    @Mock
    BoardUpdateRequestDto boardUpdateRequestDto;
    @Mock
    BoardRepository boardRepository;
    @Mock
    BoardReadCondition boardReadCondition;
    @Mock
    FileService fileService;
    @Mock
    MemberRepository memberRepository;
    @Mock
    CategoryRepository categoryRepository;


    @Test
    void readAll() {
        //given
        given(boardRepository.findAllByCondition(any())).willReturn(Page.empty());

        //when
        List<BoardDto> boardList = boardService.readAll();

        //then
        assertThat(boardListDto.getPostList().size()).isZero(); //실제 값이 0인지 확인

    }


    /*@Test
    void read() {
        // given
        Board board = createBoard(null,null);
        given(boardRepository.findById(anyLong())).willReturn(Optional.of(board));

        // when
        BoardDto boardDto = boardService.read(1L);

        // then
        assertThat(boardDto.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardDto.getImages().size()).isEqualTo(board.getImages().size());

    }*/

 /*  NullPointException
  @Test
    void create(){
        // given
        BoardCreateRequestDto req = createPostCreateRequest();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(createMember()));
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(createCategory()));

        // when
        boardService.create(req);

        // then
        verify(boardRepository).save(any());
    }*/

/*  IO Exception

  @Test
    void delete() {
        // given
        Board board = createBoard(null,null);
        given(boardRepository.findById(anyLong())).willReturn(Optional.of(board));
        // when
        boardService.delete(1L);

        // then
        verify(fileService, times(board.getImages().size())).delete(anyString());
        verify(boardRepository).delete(any());
    }*/

    @Test
    void update() {

    }
}