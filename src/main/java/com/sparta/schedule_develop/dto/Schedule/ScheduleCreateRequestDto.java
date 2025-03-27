package com.sparta.schedule_develop.dto.Schedule;

import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    private final Long userId;

    private final String title;

    private final String content;

    public ScheduleCreateRequestDto(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
