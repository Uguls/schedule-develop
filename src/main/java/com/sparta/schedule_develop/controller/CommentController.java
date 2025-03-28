package com.sparta.schedule_develop.controller;


import com.sparta.schedule_develop.dto.comment.CommentCreateRequestDto;
import com.sparta.schedule_develop.dto.comment.CommentResponseDto;
import com.sparta.schedule_develop.dto.comment.CommentUpdateRequestDto;
import com.sparta.schedule_develop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<CommentResponseDto> saveSchedule(@Valid @RequestBody CommentCreateRequestDto dto) {
        CommentResponseDto save = commentService.save(dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentResponseDto>> findAllSchedule() {
        List<CommentResponseDto> all = commentService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findScheduleById(@PathVariable Long id) {
        CommentResponseDto schedule = commentService.findById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateScheduleById(
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequestDto dto
    ) {
        CommentResponseDto updated = commentService.updateById(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable Long id) {
        commentService.deleteById(id);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }

}
