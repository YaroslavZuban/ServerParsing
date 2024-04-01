package com.example.serverparsing.repository;

import com.example.serverparsing.dto.PersonDataDto;
import com.example.serverparsing.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
    @Query(value = "SELECT * " +
            "FROM (SELECT *, ROW_NUMBER()" +
            "OVER" +
            "(ORDER BY id DESC) AS row_num" +
            " FROM personal_data) AS sub" +
            " WHERE row_num BETWEEN :start AND :end"
            , nativeQuery = true)
    List<PersonalData> findAll(int start, int end);
}
