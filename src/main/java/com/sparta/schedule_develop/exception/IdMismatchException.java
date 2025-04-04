package com.sparta.schedule_develop.exception;

public class IdMismatchException extends IllegalArgumentException {
    public IdMismatchException() {
        super("Id가 일치하지 않습니다.");
    }
}
