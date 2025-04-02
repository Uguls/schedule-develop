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
    public UserResponseDto updateById(Long id, UserUpdateAndDeleteRequestDto dto, User loginUser) {
        // 영속상태 진입
        User user = userRepository.findById(loginUser.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + id));

        if (!user.getPassword().equals(loginUser.getPassword())) {
            throw new PasswordMismatchException();
        }

        // 영속 상태의 객체 값 변경
        user.update(dto.getName(), dto.getEmail());

        /*
        * 트랜잭션의 종료 될 때 JPA가 영속 상태의 객체를 스냅샷과 비교하여 변경을 감지
        * update 쿼리를 자동으로 실행
        */
        return new UserResponseDto(user);
    }


    @Transactional
    public void deleteById(User loginUser) {
        User user = userRepository.findByEmailAndName(loginUser.getEmail(), loginUser.getName()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + loginUser.getName()));

        /*
         * 유저를 삭제하기 위해 schedule을 삭제하여야 함
         * schedule을 삭제하기 위해서는 comment를 삭제하여야 함
         * 즉 comment -> schedule -> user 순으로 삭제하여야 함
         */
        commentRepository.deleteAllByUser_Id(user.getId());
        scheduleRepository.deleteAllByUser_Id(user.getId());
        userRepository.delete(user);
    }

    public UserResponseDto login(LoginRequestDto dto, HttpServletRequest request) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다." + dto.getEmail()));

        if (!PasswordUtil.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        request.getSession(true).setAttribute("user", user);

        return new UserResponseDto(user);
    }
}
