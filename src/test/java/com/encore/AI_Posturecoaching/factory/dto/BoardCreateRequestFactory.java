package com.encore.AI_Posturecoaching.factory.dto;

import com.encore.AI_Posturecoaching.board.dto.BoardCreateRequestDto;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BoardCreateRequestFactory {
    public static BoardCreateRequestDto createPostCreateRequest() {
        return new BoardCreateRequestDto("title", "content", 1L, 1L, List.of(
                new MockMultipartFile("test1", "test1.PNG", MediaType.IMAGE_PNG_VALUE, "test1".getBytes()),
                new MockMultipartFile("test2", "test2.PNG", MediaType.IMAGE_PNG_VALUE, "test2".getBytes()),
                new MockMultipartFile("test3", "test3.PNG", MediaType.IMAGE_PNG_VALUE, "test3".getBytes())
        ));
    }

    public static BoardCreateRequestDto createPostCreateRequest(String title, String content, Long memberId, Long categoryId, List<MultipartFile> images) {
        return new BoardCreateRequestDto(title, content, memberId, categoryId, images);
    }



}
