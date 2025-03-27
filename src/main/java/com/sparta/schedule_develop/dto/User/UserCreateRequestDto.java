package com.sparta.schedule_develop.dto.User;

import lombok.Getter;

@Getter
public class UserCreateRequestDto {

    private final String name;

    private final String email;

    private final String password;

    public UserCreateRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
