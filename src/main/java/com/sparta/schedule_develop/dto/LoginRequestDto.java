package com.sparta.schedule_develop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "이메일은 필수입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
