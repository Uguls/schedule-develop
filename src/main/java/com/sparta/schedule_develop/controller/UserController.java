package com.sparta.schedule_develop.controller;

import com.sparta.schedule_develop.dto.LoginRequestDto;
import com.sparta.schedule_develop.dto.User.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.User.UserResponseDto;
import com.sparta.schedule_develop.dto.User.UserUpdateAndDeleteRequestDto;
import com.sparta.schedule_develop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserCreateRequestDto dto) {
        UserResponseDto saved = userService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request) {
        userService.login(dto, request);
        return null;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> findAllUser() {
        List<UserResponseDto> all = userService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        UserResponseDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateAndDeleteRequestDto dto
    ) {
        UserResponseDto updated = userService.updateById(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteUserById(@Valid @RequestBody UserUpdateAndDeleteRequestDto dto) {
        userService.deleteById(dto);
        return new ResponseEntity<>( dto.getName() + " 유저 삭제 성공", HttpStatus.OK);
    }
}
