package com.example.serverparsing.repository;

import com.example.serverparsing.entity.PersonDataDto;
import com.example.serverparsing.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
    @Query(value = "SELECT sub.id AS id, name, title, wages, skills " +
            "FROM (SELECT *, ROW_NUMBER() " +
            "OVER (ORDER BY id DESC) AS row_num " +
            "FROM personal_data) AS sub " +
            "LEFT JOIN add_information ON add_information.id = sub.add_information_id " +
            "WHERE row_num BETWEEN :start AND :end", nativeQuery = true)
    List<Object[]> findAllDto(int start, int end);

    default List<PersonDataDto> findDtoAll(int start, int end) {
        List<Object[]> results = findAllDto(start, end);
        List<PersonDataDto> personDataDtoList = new ArrayList<>();

        for (Object[] result : results) {
            int id = (int) result[0];
            String name = (String) result[1];
            String title = (String) result[2];
            Integer wages = (Integer) result[3];
            String skills = (String) result[4];
            personDataDtoList.add(new PersonDataDto(id, name, title, wages, skills));
        }

        return personDataDtoList;
    }
}
