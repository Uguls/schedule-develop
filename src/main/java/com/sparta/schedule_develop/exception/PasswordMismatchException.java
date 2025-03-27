package com.sparta.schedule_develop.exception;

public class PasswordMismatchException extends IllegalArgumentException {
    public PasswordMismatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
