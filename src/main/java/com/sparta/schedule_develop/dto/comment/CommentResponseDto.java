package com.sparta.schedule_develop.dto.comment;

import com.sparta.schedule_develop.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final String content;
    private final String user_name;
    private final String schedule_name;
    private final LocalDateTime created_date;
    private final LocalDateTime updated_date;

    public CommentResponseDto(Comment comment) {
        this.schedule_name = comment.getSchedule().getTitle();
        this.content = comment.getContent();
        this.user_name = comment.getUser().getName();
        this.created_date = comment.getCreatedAt();
        this.updated_date = comment.getUpdatedAt();
    }

}
