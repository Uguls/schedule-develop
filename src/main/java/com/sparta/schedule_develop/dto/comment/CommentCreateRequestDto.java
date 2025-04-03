package com.sparta.schedule_develop.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    private Long scheduleId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 200, message = "댓글 내용은 200자 이내여야 합니다.")
    private String content;
}
