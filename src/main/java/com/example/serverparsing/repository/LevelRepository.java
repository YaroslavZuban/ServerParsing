package com.example.serverparsing.repository;

import com.example.serverparsing.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Query(value = "select knowledge_level from level ORDER BY knowledge_level", nativeQuery = true)
    List<String> getLanguageLevelAll();
}
