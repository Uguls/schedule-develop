package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.ScheduleCreateRequestDto;
import com.sparta.schedule_develop.dto.ScheduleCreateResponseDto;
import com.sparta.schedule_develop.entity.Schedule;
import com.sparta.schedule_develop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleCreateResponseDto save(ScheduleCreateRequestDto dto) {
        Schedule schedule = new Schedule(dto.getUser_name(), dto.getTitle(), dto.getContent());
        Schedule saved = scheduleRepository.save(schedule);
        return new ScheduleCreateResponseDto(saved);
    }
}
