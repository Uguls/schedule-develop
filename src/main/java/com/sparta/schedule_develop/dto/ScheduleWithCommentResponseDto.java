package com.sparta.schedule_develop.dto;

import com.sparta.schedule_develop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleWithCommentResponseDto {
    private Long id;
    private String title;
    private String description;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private long commentCount;

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
}
