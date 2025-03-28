package com.sparta.schedule_develop.controller;


import com.sparta.schedule_develop.dto.ScheduleWithCommentResponseDto;
import com.sparta.schedule_develop.dto.schedule.ScheduleCreateRequestDto;
import com.sparta.schedule_develop.dto.schedule.ScheduleResponseDto;
import com.sparta.schedule_develop.dto.schedule.ScheduleUpdateRequestDto;
import com.sparta.schedule_develop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * @param dto 할일 제목, 할일 내용
     * @return 제목, 내용, 작성자명, 생성날짜, 수정날짜
     */
    @PostMapping("/")
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@Valid @RequestBody ScheduleCreateRequestDto dto) {
        ScheduleResponseDto save = scheduleService.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    /**
     * @param page 현재 페이지
     * @param size 몇개씩 조회할지
     * @return 모든 일정(제목, 내용, 작성자명, 생성날짜, 수정날짜, 댓글 수)
     */
    @GetMapping("/")
    public ResponseEntity<List<ScheduleWithCommentResponseDto>> findAllSchedule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
//        List<ScheduleResponseDto> all = scheduleService.findAll();
        List<ScheduleWithCommentResponseDto> all = scheduleService.findAllWithCommentCount(pageable);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /**
     * @param id 조회할 일정 id
     * @return 제목, 내용, 작성자명, 생성날짜, 수정날짜
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.findById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    /**
     * @param id 수정할 일정 id
     * @param dto 일정 제목, 내용
     * @return 수정된 제목, 내용, 작성자명, 생성날짜, 수정날짜
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleUpdateRequestDto dto
    ) {
        ScheduleResponseDto updated = scheduleService.updateById(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * @param id 삭제할 일정 id
     * @return 일정 삭제 성공 여부
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return new ResponseEntity<>("일정 삭제 성공", HttpStatus.OK);
    }

}
