package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Position;
import com.dohyeon.kiosk.validation.JoinUp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    @NotEmpty(message = "아이디는 필수 입력값입니다")
    private String user_id;

    @NotEmpty(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    private String position;

}
