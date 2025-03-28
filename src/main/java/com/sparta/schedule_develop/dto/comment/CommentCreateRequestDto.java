package com.sparta.schedule_develop.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    private final Long schedulId;

    private final Long userId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 200, message = "댓글 내용은 200자 이내여야 합니다.")
    private final String content;

    public CommentCreateRequestDto(Long schedulId, Long userId, String content) {
        this.schedulId = schedulId;
        this.userId = userId;
        this.content = content;
    }
}
