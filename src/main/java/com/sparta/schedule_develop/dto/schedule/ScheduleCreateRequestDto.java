package com.sparta.schedule_develop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ScheduleCreateRequestDto {
    @NotBlank(message = "할일 제목은 필수입니다.")
    @Size(max = 200, message = "할일 제목은 200자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "할일 내용은 필수입니다.")
    @Size(max = 200, message = "할일 내용은 200자 이내여야 합니다.")
    private String content;
}
