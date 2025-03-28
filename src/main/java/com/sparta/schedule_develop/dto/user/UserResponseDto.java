package com.sparta.schedule_develop.dto.user;

import com.sparta.schedule_develop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final String name;

    private final String email;

    private final Long id;

    private final LocalDateTime created_date;

    private final LocalDateTime updated_date;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.id = user.getId();
        this.created_date = user.getCreatedAt();
        this.updated_date = user.getUpdatedAt();
    }
}
