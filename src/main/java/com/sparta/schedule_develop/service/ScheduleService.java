package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.ScheduleCreateRequestDto;
import com.sparta.schedule_develop.dto.ScheduleResponseDto;
import com.sparta.schedule_develop.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule_develop.entity.Schedule;
import com.sparta.schedule_develop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(ScheduleCreateRequestDto dto) {
        Schedule schedule = new Schedule(dto.getUser_name(), dto.getTitle(), dto.getContent());
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
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateByid(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        schedule.update(dto.getTitle(), dto.getContent());


        return new ScheduleResponseDto(schedule);
    }
}
