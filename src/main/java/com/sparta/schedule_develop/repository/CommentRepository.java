package com.sparta.schedule_develop.repository;

import com.sparta.schedule_develop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByUser_Id(Long id);

    void deleteAllBySchedule_Id(Long id);

    long countByScheduleId(Long scheduleId);

    List<Comment> findAllByScheduleId(Long scheduleId);
}
