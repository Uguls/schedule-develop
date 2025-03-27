package com.sparta.schedule_develop.repository;

import com.sparta.schedule_develop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    void deleteAllByUser_Id(Long id);
}
