package com.encore.AI_Posturecoaching.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class BoardListDto {
    private Long totalElements;
    private Integer totalPages;
    private boolean hasNext;
    private List<BoardSimpleDto> postList;

    public static BoardListDto toDto(Page<BoardSimpleDto> page) {
        return new BoardListDto(page.getTotalElements(), page.getTotalPages(), page.hasNext(), page.getContent());
    }
}
