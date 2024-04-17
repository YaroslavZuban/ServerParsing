package com.example.serverparsing.repository;

import com.example.serverparsing.entity.CitizenshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenshipRepository extends JpaRepository<CitizenshipType, Integer> {
    @Query(value = "SELECT name FROM citizenship_type", nativeQuery = true)
    List<String> getCitizenshipAll();
}
