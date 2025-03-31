package com.sparta.schedule_develop.dto;

import com.sparta.schedule_develop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleWithCommentResponseDto {
    private final Long id;
    private final String title;
    private final String description;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final long commentCount;

    public ScheduleWithCommentResponseDto(Long id, String title, String description, String username,
                                          LocalDateTime createdAt, LocalDateTime modifiedAt, long commentCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentCount = commentCount;
    }

    public static ScheduleWithCommentResponseDto withCommentCount(Schedule s, long commentCount) {
        return new ScheduleWithCommentResponseDto(
                s.getId(),
                s.getTitle(),
                s.getContent(),
                s.getUser().getName(),
                s.getCreatedAt(),
                s.getUpdatedAt(),
                commentCount
        );
    }
}
