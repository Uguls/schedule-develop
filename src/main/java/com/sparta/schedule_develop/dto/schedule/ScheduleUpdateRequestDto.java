package com.sparta.schedule_develop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    @NotBlank(message = "할일 제목은 필수입니다.")
    @Size(max = 200, message = "할일 제목은 200자 이내여야 합니다.")
    private final String title;

    @NotBlank(message = "할일 내용은 필수입니다.")
    @Size(max = 200, message = "할일 내용은 200자 이내여야 합니다.")
    private final String content;

    public ScheduleUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
