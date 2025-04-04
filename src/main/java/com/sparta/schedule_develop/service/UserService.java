package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.common.SessionConst;
import com.sparta.schedule_develop.dto.LoginRequestDto;
import com.sparta.schedule_develop.dto.user.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.user.UserResponseDto;
import com.sparta.schedule_develop.dto.user.UserUpdateAndDeleteRequestDto;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.exception.IdMismatchException;
import com.sparta.schedule_develop.exception.LoginException;
import com.sparta.schedule_develop.exception.PasswordMismatchException;
import com.sparta.schedule_develop.repository.CommentRepository;
import com.sparta.schedule_develop.repository.ScheduleRepository;
import com.sparta.schedule_develop.repository.UserRepository;
import com.sparta.schedule_develop.util.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public UserResponseDto save(UserCreateRequestDto dto) {

        String encodedPw = PasswordUtil.encode(dto.getPassword());

        User user = new User(dto.getEmail(), dto.getName(), encodedPw);

        User saved = userRepository.save(user);
        return new UserResponseDto(saved);
    }

    public List<UserResponseDto> findAll() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + id));
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateById(UserUpdateAndDeleteRequestDto dto, User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        if (!user.getPassword().equals(loginUser.getPassword())) {
            throw new PasswordMismatchException();
        }

        user.update(dto.getName(), dto.getEmail());

        return new UserResponseDto(user);
    }


    @Transactional
    public void deleteById(User loginUser) {
        User user = userRepository.findByEmailAndName(loginUser.getEmail(), loginUser.getName()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + loginUser.getName()));

        // CASCADE 사용해도 됨
        commentRepository.deleteAllByUser_Id(user.getId());
        scheduleRepository.deleteAllByUser_Id(user.getId());
        userRepository.delete(user);
    }

    public UserResponseDto login(LoginRequestDto dto, HttpServletRequest request) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new IdMismatchException());

        if (!PasswordUtil.matches(dto.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException();
        }

        request.getSession(true).setAttribute(SessionConst.LOGIN_USER, user);

        return new UserResponseDto(user);
    }
}
