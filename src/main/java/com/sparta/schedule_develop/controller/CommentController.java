package com.sparta.schedule_develop.controller;


import com.sparta.schedule_develop.common.SessionConst;
import com.sparta.schedule_develop.dto.comment.CommentCreateRequestDto;
import com.sparta.schedule_develop.dto.comment.CommentResponseDto;
import com.sparta.schedule_develop.dto.comment.CommentUpdateRequestDto;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
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

    /**
     * @param dto 댓글 내용(content)
     * @return 댓글 작성 성공 시
     * 내용, 작성자명, 일정 이름, 작성날짜, 수정날짜
     */
    @PostMapping("/")
    public ResponseEntity<CommentResponseDto> saveComment(@Valid @RequestBody CommentCreateRequestDto dto, HttpServletRequest request) {
        User loginUser = (User) request.getSession(false).getAttribute(SessionConst.LOGIN_USER);
        CommentResponseDto save = commentService.save(dto, loginUser);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    /**
     * @return 전체 댓글 목록 출력
     */
    @GetMapping("/")
    public ResponseEntity<List<CommentResponseDto>> findAllComment() {
        List<CommentResponseDto> all = commentService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /**
     * @param scheduleId 댓글이 작성된 스케줄의 id
     * @return 내용, 작성자명, 일정 이름, 작성날짜, 수정날짜
     */
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findCommentsByScheduleId(@PathVariable Long scheduleId) {
        List<CommentResponseDto> comments = commentService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * @param id  수정할 댓글 id
     * @param dto 수정할 댓글 내용
     * @return 수정된 댓글 내용, 작성자명, 일정 이름, 작성날짜, 수정날짜
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateCommentById(
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequestDto dto,
            HttpServletRequest request
    ) {
        User loginUser = (User) request.getSession(false).getAttribute(SessionConst.LOGIN_USER);
        CommentResponseDto updated = commentService.updateById(id, dto, loginUser);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * @param id 삭제할 댓글 id
     * @return 댓글 삭제 성공 여부
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long id, HttpServletRequest request) {
        User loginUser = (User) request.getSession(false).getAttribute(SessionConst.LOGIN_USER);
        commentService.deleteById(id, loginUser);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }

}
