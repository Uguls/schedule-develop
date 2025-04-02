package com.sparta.schedule_develop.controller;

import com.sparta.schedule_develop.dto.LoginRequestDto;
import com.sparta.schedule_develop.dto.user.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.user.UserResponseDto;
import com.sparta.schedule_develop.dto.user.UserUpdateAndDeleteRequestDto;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        UserResponseDto logined = userService.login(dto, request);
        return new ResponseEntity<>(logined, HttpStatus.OK);
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
     * @param id  수정할 유저 id
     * @param dto 수정할 유저의 이름, 이메일, 비밀번호
     * @return 이름, 이메일, 작성자 id, 생성날짜, 수정날짜
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateAndDeleteRequestDto dto,
            HttpServletRequest request
    ) {
        User loginUser = (User) request.getSession(false).getAttribute("user");
        UserResponseDto updated = userService.updateById(id, dto, loginUser);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * @param dto 삭제할 유저의 id
     * @return 유저 삭제 성공 여부
     */
    @DeleteMapping("/")
    public ResponseEntity<String> deleteUserById(HttpServletRequest request) {
        User loginUser = (User) request.getSession(false).getAttribute("user");
        userService.deleteById(loginUser);
        return new ResponseEntity<>(loginUser.getName() + " 유저 삭제 성공", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 완료");
    }

}
