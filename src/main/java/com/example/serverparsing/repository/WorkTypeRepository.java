package com.example.serverparsing.repository;

import com.example.serverparsing.entity.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkSchedule, Integer> {
    @Query(value = "SELECT work_type FROM work_schedule", nativeQuery = true)
    List<String> getWorkScheduleAll();
}
