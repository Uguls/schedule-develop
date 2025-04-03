package com.sparta.schedule_develop.service;

import com.sparta.schedule_develop.dto.comment.CommentCreateRequestDto;
import com.sparta.schedule_develop.dto.comment.CommentResponseDto;
import com.sparta.schedule_develop.dto.comment.CommentUpdateRequestDto;
import com.sparta.schedule_develop.entity.Comment;
import com.sparta.schedule_develop.entity.Schedule;
import com.sparta.schedule_develop.entity.User;
import com.sparta.schedule_develop.repository.CommentRepository;
import com.sparta.schedule_develop.repository.ScheduleRepository;
import com.sparta.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto save(CommentCreateRequestDto dto, User loginUser) {

        User user = userRepository.findById(loginUser.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Schedule schedule = scheduleRepository.findById(dto.getScheduleId()).orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        Comment comment = new Comment(schedule, user, dto.getContent());

        Comment saved = commentRepository.save(comment);

        return new CommentResponseDto(saved);
    }

    public List<CommentResponseDto> findAll() {
        List<Comment> all = commentRepository.findAll();
        return all.stream().map(CommentResponseDto::new).toList();
    }

    public CommentResponseDto findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다." + id));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateById(Long id, CommentUpdateRequestDto dto, User loginUser) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다." + id));

        if (!comment.getUser().getId().equals(loginUser.getId())) {
            throw new IllegalStateException("댓글을 수정할 권한이 없습니다.");
        }

        comment.update(dto.getContent());

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteById(Long id, User loginUser) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다." + id));

        if (!comment.getUser().getId().equals(loginUser.getId())) {
            throw new IllegalStateException("댓글을 삭제할 권한이 없습니다.");
        }
        
        commentRepository.delete(comment);
    }

}
