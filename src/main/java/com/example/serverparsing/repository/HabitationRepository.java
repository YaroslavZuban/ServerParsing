package com.example.serverparsing.repository;

import com.example.serverparsing.entity.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Integer> {
    @Query(value = "SELECT city FROM habitation ORDER BY city", nativeQuery = true)
    List<String> getCityAll();
}
