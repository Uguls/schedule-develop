package com.sparta.schedule_develop.controller;


import com.sparta.schedule_develop.dto.ScheduleCreateRequestDto;
import com.sparta.schedule_develop.dto.ScheduleCreateResponseDto;
import com.sparta.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/")
    public ResponseEntity<ScheduleCreateResponseDto> save(@RequestBody ScheduleCreateRequestDto dto){
        ScheduleCreateResponseDto save = scheduleService.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

}
