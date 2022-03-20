package com.encore.AI_Posturecoaching.board.repository;

import com.encore.AI_Posturecoaching.board.dto.BoardReadCondition;
import com.encore.AI_Posturecoaching.board.dto.BoardSimpleDto;
import org.springframework.data.domain.Page;

public interface CustomBoardRepository {
    Page<BoardSimpleDto> findAllByCondition(BoardReadCondition cond);
}
