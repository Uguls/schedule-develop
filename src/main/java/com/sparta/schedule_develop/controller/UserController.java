package com.sparta.schedule_develop.controller;

import com.sparta.schedule_develop.dto.*;
import com.sparta.schedule_develop.service.UserService;
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

    @PostMapping("/")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserCreateRequestDto dto) {
        UserResponseDto saved = userService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
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
            @RequestBody UserUpdateRequestDto dto
    ) {
        UserResponseDto updated = userService.updateById(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>( id + " 유저 삭제 성공", HttpStatus.OK);
    }
}
