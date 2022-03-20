package com.encore.AI_Posturecoaching.board.dto;

import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.file.File;
import com.encore.AI_Posturecoaching.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private MemberDto member;
    private List<ImageDto> images;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    public static BoardDto toDto(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                MemberDto.toDto(board.getMember()),
                board.getImages().stream().map(i -> ImageDto.toDto(i)).collect(toList()),
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }

}
