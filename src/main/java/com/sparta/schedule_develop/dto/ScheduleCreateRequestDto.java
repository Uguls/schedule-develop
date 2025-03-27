package com.sparta.schedule_develop.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {
    private final String title;

    private final String content;

    private final String user_name;

    public ScheduleCreateRequestDto(String title, String content, String user_name) {
        this.title = title;
        this.content = content;
        this.user_name = user_name;
    }
}
