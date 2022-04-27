package com.encore.AI_Posturecoaching.factory.entity;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.Image;
import com.encore.AI_Posturecoaching.board.dto.BoardDto;
import com.encore.AI_Posturecoaching.board.dto.BoardUpdateRequestDto;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.member.Member;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.encore.AI_Posturecoaching.category.CategoryFactory.createCategory;
import static com.encore.AI_Posturecoaching.factory.entity.CategoryFactory.createCategoryWithId;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;


public class BoardFactory {

    public static Board createBoard(Member member,Category category) {
        return new Board("title", "content",member, category,List.of());
    }
    public static Board deleteBoard(Member member,Category category,Board board) {
        Board deleteBoard =  new Board("title", "content",member, category,List.of());
        ReflectionTestUtils.setField(deleteBoard, "id", 3L);
        return deleteBoard;
    }
    public static Board createBoardWithImages(List<Image> images) {
        return new Board("title", "content", createMember(), createCategory(), images);
    }
    public static Board createBoardWithContent(String content) {
        return new Board("title", "content", createMember(), createCategory(),List.of());
    }
    /*public static Board createBoardWithId(Long Id) {
        Board board = new Board("title", "content", createMemberWithId(3L), createCategoryWithId(3L),List.of());
        ReflectionTestUtils.setField(board, "id", 3L);
        return board;

    }*/

    public static BoardUpdateRequestDto updateRequest(String test) {
        BoardUpdateRequestDto board = new BoardUpdateRequestDto("title", "content",null,null);
        ReflectionTestUtils.setField(board, "id", 2L);
        return board;
    };
}

