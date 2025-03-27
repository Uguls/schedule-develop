package com.sparta.schedule_develop.dto;

import lombok.Getter;

@Getter
public class UserCreateRequestDto {

    private final String name;

    private final String email;

    public UserCreateRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
