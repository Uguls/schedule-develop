package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.LoginRequestDto;
import com.sparta.schedule_develop.dto.user.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.user.UserResponseDto;
import com.sparta.schedule_develop.dto.user.UserUpdateAndDeleteRequestDto;
import com.sparta.schedule_develop.entity.User;
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
    public UserResponseDto updateById(Long id, UserUpdateAndDeleteRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + id));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw  new PasswordMismatchException();
        }

        user.update(dto.getName(), dto.getEmail());

        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteById(UserUpdateAndDeleteRequestDto dto) {
        User user = userRepository.findByEmailAndName(dto.getEmail(), dto.getName()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + dto.getName()));

        scheduleRepository.deleteAllByUser_Id(user.getId());
        commentRepository.deleteAllByUser_Id(user.getId());

        userRepository.delete(user);
    }

    public void login(LoginRequestDto dto, HttpServletRequest request) {
        User user = userRepository.findByEmailAndName(dto.getEmail(), dto.getName()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + dto.getEmail()));

        if (!PasswordUtil.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        request.getSession(true).setAttribute("user", user);

    }
}
