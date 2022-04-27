package com.encore.AI_Posturecoaching.sign.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "로그인 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {
    @ApiModelProperty(value = "이메일", notes = "이메일을 입력해주세요", required = true, example = "member@email.com")
    private String email;

    @ApiModelProperty(value = "비밀번호", notes = "비밀번호는 최소 8자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.", required = true, example = "123456a!")
    private String password;

}
