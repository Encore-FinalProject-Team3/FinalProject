package com.encore.AI_Posturecoaching.board.service;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.dto.*;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.file.service.FileService;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static com.encore.AI_Posturecoaching.category.CategoryFactory.createCategory;
import static com.encore.AI_Posturecoaching.factory.dto.BoardCreateRequestFactory.createPostCreateRequest;
import static com.encore.AI_Posturecoaching.factory.dto.BoardReadConditionFactory.createPostReadCondition;

import static com.encore.AI_Posturecoaching.factory.entity.BoardFactory.*;
import static com.encore.AI_Posturecoaching.factory.entity.CategoryFactory.createCategoryWithId;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMemberWithId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
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
    BoardUpdateRequestDto boardUpdateRequest;
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




    /*@Test
    void readAll() {
        //given
        given(boardRepository.findAllByCondition(any())).willReturn(Page.empty());

        //when
        BoardListDto boardListDto = boardService.readAll(createPostReadCondition(1,1));

        //then
        assertThat(boardListDto.getPostList().size()).isZero(); //실제 값이 0인지 확인

    }*/


    @Test
    void read() {
        // given
        Member member = new Member("aaa@aaa","1111","222","admin");
        Category category = new Category("test1",null);
        Board board = createBoard(member,category);
        given(boardRepository.findById(anyLong())).willReturn(Optional.of(board));

        // when
        BoardDto boardDto = boardService.read(1L);

        // then
        assertThat(boardDto.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardDto.getImages().size()).isEqualTo(board.getImages().size());

    }

   /*@Test
    void create(){
        // given
        Member member = new Member("aaa@aaa","1111","222","admin");
        Category category = new Category("test1",null);
        Board board = createBoard(member,category);
        given(boardRepository.findById(anyLong())).willReturn(Optional.of(board));

       // when
       //boardService.create(board);

        // then
        verify(boardRepository).save(any());
    }*/




   @Test//  IO Exception
    void delete() {
        // given
        // Member member = new Member("member1@member1.com","123456!","222","admin");
        Category category = createCategoryWithId(3L);
        Member member = createMemberWithId(3L);
        Board board = createBoardWithId(3L);
        Board board1 = deleteBoard(member,category,board);

        given(boardRepository.findById(3L)).willReturn(Optional.of(board1));
        // when
        boardService.delete("3",3L);

        // then

        verify(boardRepository).delete(any());
    }

   /* @Test
    void update() {
       //given
       BoardUpdateRequestDto burDto = updateRequest("");
       Board board = createBoardWithContent("test");
       given(boardRepository.findById(5L)).willReturn(Optional.of(board));

       //when
       boardService.update("5",5L,burDto);

       //then
        verify(boardRepository).save(any());


    }*/
}