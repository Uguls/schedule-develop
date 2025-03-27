package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.Schedule.ScheduleCreateRequestDto;
import com.sparta.schedule_develop.dto.Schedule.ScheduleResponseDto;
import com.sparta.schedule_develop.dto.Schedule.ScheduleUpdateRequestDto;
import com.sparta.schedule_develop.entity.Schedule;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.repository.ScheduleRepository;
import com.sparta.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(ScheduleCreateRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Schedule schedule = new Schedule(user, dto.getTitle(), dto.getContent());

        Schedule saved = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(saved);
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> all = scheduleRepository.findAll();
        return all.stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다." + id));
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateById(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다." + id));

        schedule.update(dto.getTitle(), dto.getContent());


        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public void deleteById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다." + id));

        scheduleRepository.delete(schedule);

    }
}
