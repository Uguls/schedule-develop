package com.sparta.schedule_develop.dto.schedule;

import com.sparta.schedule_develop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final String title;

    private final String content;

    private final String user_name;

    private final LocalDateTime created_date;

    private final LocalDateTime updated_date;

    public ScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.user_name = schedule.getUser().getName();
        this.created_date = schedule.getCreatedAt();
        this.updated_date = schedule.getUpdatedAt();
    }

}
