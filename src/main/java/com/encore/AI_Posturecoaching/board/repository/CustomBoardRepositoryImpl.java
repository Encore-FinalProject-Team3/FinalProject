package com.encore.AI_Posturecoaching.board.repository;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.dto.BoardReadCondition;
import com.encore.AI_Posturecoaching.board.dto.BoardSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public class CustomBoardRepositoryImpl implements CustomBoardRepository {


    @Override
    public Page<BoardSimpleDto> findAllByCondition(BoardReadCondition cond) {
        return null;
    }



}
