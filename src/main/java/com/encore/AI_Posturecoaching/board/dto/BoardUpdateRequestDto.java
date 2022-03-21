package com.encore.AI_Posturecoaching.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "게시글 수정 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDto {
    @ApiModelProperty(value = "게시글 제목", notes = "게시글 제목을 입력해주세요", required = true, example = "my title")
    private String title;

    @ApiModelProperty(value = "게시글 본문", notes = "게시글 본문을 입력해주세요", required = true, example = "my content")
    private String content;

    @ApiModelProperty(value = "추가된 이미지", notes = "추가된 이미지를 첨부해주세요.")
    private List<MultipartFile> addedImages = new ArrayList<>();

    @ApiModelProperty(value = "제거된 이미지 아이디", notes = "제거된 이미지 아이디를 입력해주세요.")
    private List<Long> deletedImages = new ArrayList<>();
}
