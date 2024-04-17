package com.example.serverparsing.repository;

import com.example.serverparsing.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
    @Query(value = "SELECT type FROM gender", nativeQuery = true)
    List<String> getGenderAll();
}
