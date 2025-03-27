package com.sparta.schedule_develop.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private final String name;
    private final String email;

    public UserUpdateRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
