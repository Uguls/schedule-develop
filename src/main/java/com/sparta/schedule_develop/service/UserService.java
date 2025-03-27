package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.UserCreateRequestDto;
import com.sparta.schedule_develop.dto.UserResponseDto;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(UserCreateRequestDto dto) {
        User user = new User(dto.getEmail(), dto.getName());

        User saved = userRepository.save(user);

        return new UserResponseDto(saved);
    }

}
