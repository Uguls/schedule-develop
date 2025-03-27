package com.sparta.schedule_develop.repository;

import com.sparta.schedule_develop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
