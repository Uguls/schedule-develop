package com.sparta.schedule_develop.controller;

import com.sparta.schedule_develop.dto.LoginRequestDto;
import com.sparta.schedule_develop.dto.user.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.user.UserResponseDto;
import com.sparta.schedule_develop.dto.user.UserUpdateAndDeleteRequestDto;
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

    /**
     * @param dto 이름, 이메일, 비밀번호
     * @return 이름, 이메일, 작성자 id, 생성날짜, 수정날짜
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserCreateRequestDto dto) {
        UserResponseDto saved = userService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * @param dto 이름, 이메일, 비밀번호
     * @return 로그인 성공여부
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request) {
        userService.login(dto, request);
        //TODO - 반환 작성
        return null;
    }

    /**
     * @return 모든 유저 조회
     */
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> findAllUser() {
        List<UserResponseDto> all = userService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /**
     * @param id 조회할 유저 id
     * @return 이름, 이메일, 작성자 id, 생성날짜, 수정날짜
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        UserResponseDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * @param id 수정할 유저 id
     * @param dto 수정할 유저의 이름, 이메일, 비밀번호
     * @return 이름, 이메일, 작성자 id, 생성날짜, 수정날짜
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateAndDeleteRequestDto dto
    ) {
        UserResponseDto updated = userService.updateById(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * @param dto 삭제할 유저의 id
     * @return 유저 삭제 성공 여부
     */
    @DeleteMapping("/")
    public ResponseEntity<String> deleteUserById(@Valid @RequestBody UserUpdateAndDeleteRequestDto dto) {
        userService.deleteById(dto);
        return new ResponseEntity<>( dto.getName() + " 유저 삭제 성공", HttpStatus.OK);
    }
}
