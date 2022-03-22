package com.encore.AI_Posturecoaching.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "사용자 정보 수정 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequestDto {
    @ApiModelProperty(value = "사용자 정보 비밀번호", notes = "사용자 정보 새로 저장할 비밀번호을 입력해주세요", required = true, example = "my password")
    private String password;
    @ApiModelProperty(value = "사용자 정보 이름", notes = "사용자 정보 제목을 입력해주세요", required = true, example = "my memberName")
    private String memberName; // 이름
    @ApiModelProperty(value = "사용자 정보 생일", notes = "사용자 정보 생일을 입력해주세요", required = true, example = "my brith")
    private Timestamp brith;  // 생일
    @ApiModelProperty(value = "사용자 정보 핸드폰", notes = "사용자 정보 핸드폰번호를 입력해주세요", required = true, example = "my phone")
    private String phone;   // 핸드폰
    @ApiModelProperty(value = "사용자 정보 성별", notes = "사용자 정보 성별을 선택해주세요", required = true, example = "my gender")
    private int gender;  // 성별
    @ApiModelProperty(value = "사용자 정보 주소", notes = "사용자 정보 주소를 입력해주세요", required = true, example = "my address")
    private String address; // 주소

    @ApiModelProperty(value = "추가된 이미지", notes = "추가된 이미지를 첨부해주세요.")
    private List<MultipartFile> addedImages = new ArrayList<>();

    @ApiModelProperty(value = "제거된 이미지 아이디", notes = "제거된 이미지 아이디를 입력해주세요.")
    private List<Long> deletedImages = new ArrayList<>();

}
