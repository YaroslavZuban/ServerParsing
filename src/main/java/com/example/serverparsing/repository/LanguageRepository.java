package com.example.serverparsing.repository;

import com.example.serverparsing.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Query(value = "select l.name from language as l", nativeQuery = true)
    List<String> getAllName();
}
