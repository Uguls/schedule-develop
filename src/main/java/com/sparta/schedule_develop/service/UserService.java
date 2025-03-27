package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.UserResponseDto;
import com.sparta.schedule_develop.dto.UserUpdateRequestDto;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.repository.ScheduleRepository;
import com.sparta.schedule_develop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public UserResponseDto save(UserCreateRequestDto dto) {
        User user = new User(dto.getEmail(), dto.getName());

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
    public UserResponseDto updateById(Long id, UserUpdateRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다." + id));

        user.update(dto.getName(), dto.getEmail());

        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다." + id));

        scheduleRepository.deleteAllByUser_Id(id);

        userRepository.delete(user);
    }
}
