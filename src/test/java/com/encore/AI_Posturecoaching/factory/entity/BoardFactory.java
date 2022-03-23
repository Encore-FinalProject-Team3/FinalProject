package com.encore.AI_Posturecoaching.factory.entity;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.Image;
import com.encore.AI_Posturecoaching.board.dto.BoardDto;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.member.Member;

import java.util.List;

import static com.encore.AI_Posturecoaching.category.CategoryFactory.createCategory;
import static com.encore.AI_Posturecoaching.factory.entity.MemberFactory.createMember;


public class BoardFactory {

    public static Board createBoard(Member member,Category category) {
        return new Board("title", "content",member, category,List.of());
    }
    public static Board createPostWithImages(List<Image> images) {
        return new Board("title", "content", createMember(), createCategory(), images);
    }

}

