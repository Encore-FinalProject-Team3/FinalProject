package com.encore.AI_Posturecoaching.factory.dto;

import com.encore.AI_Posturecoaching.board.dto.BoardReadCondition;

import java.util.List;

public class BoardReadConditionFactory {
    public static BoardReadCondition createPostReadCondition(Integer page, Integer size) {
        return new BoardReadCondition(page, size, List.of(), List.of());

    }
}