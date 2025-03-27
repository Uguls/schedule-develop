package com.sparta.schedule_develop.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
        super("로그인이 필요합니다.");
    }
}
