package com.example.serverparsing.repository;

import com.example.serverparsing.entity.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EducationLevelRepository extends JpaRepository<EducationType, Integer> {
    @Query(value = "SELECT education_level FROM education_type ORDER BY education_level", nativeQuery = true)
    List<String> getEducationTypeAll();
}
