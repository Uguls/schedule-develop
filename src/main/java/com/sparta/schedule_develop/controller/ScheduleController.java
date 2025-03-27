package com.sparta.schedule_develop.controller;


import com.sparta.schedule_develop.dto.ScheduleCreateRequestDto;
import com.sparta.schedule_develop.dto.ScheduleResponseDto;
import com.sparta.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/")
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleCreateRequestDto dto) {
        ScheduleResponseDto save = scheduleService.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {
        List<ScheduleResponseDto> all = scheduleService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
