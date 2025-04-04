package com.sparta.schedule_develop.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
    private String code;
    private String message;
    private String status;

    public ErrorResponseDto(String code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
