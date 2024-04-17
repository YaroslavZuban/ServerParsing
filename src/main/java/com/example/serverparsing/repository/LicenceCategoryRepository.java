package com.example.serverparsing.repository;

import com.example.serverparsing.entity.LicenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenceCategoryRepository extends JpaRepository<LicenceCategory, Integer> {
    @Query(value = "SELECT category FROM licence_category", nativeQuery = true)
    List<String> getLicenceCategoriesAll();
}
