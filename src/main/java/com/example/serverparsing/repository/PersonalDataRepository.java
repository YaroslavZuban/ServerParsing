package com.example.serverparsing.repository;

import com.example.serverparsing.entity.PersonDataDto;
import com.example.serverparsing.entity.PersonalData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Query(value = "SELECT count(*) " +
            "FROM personal_data as p " +
            "         INNER JOIN (SELECT id " +
            "                     FROM add_information " +
            "                     where lower(add_information.about_me) LIKE lower(concat('%', :skill, '%')) " +
            "                        OR lower(add_information.skills) LIKE lower(concat('%', :skill, '%')) " +
            "                        OR lower(add_information.courses_and_trainings) LIKE lower(concat('%', :skill, '%'))) as a " +
            "                    ON p.add_information_id = a.id " +
            "         INNER JOIN (SELECT education.person_data_id " +
            "                     FROM education\n" +
            "                              INNER JOIN (SELECT specification.id " +
            "                                          FROM specification " +
            "                                          WHERE lower(specification.educational_institution) LIKE " +
            "                                                lower(concat('%', :university, '%'))) as s " +
            "                                         on education.specification_id = s.id) as e " +
            "                    ON p.id = e.person_data_id", nativeQuery = true)
    Integer getSkillsUniversity(@Param("university") String university, @Param("skill") String skill);

    @Query(value = "SELECT count(*) " +
            "FROM personal_data as p " +
            "         INNER JOIN (SELECT id " +
            "                     FROM add_information " +
            "                     where lower(add_information.about_me) LIKE lower(concat('%', :skill, '%')) " +
            "                        OR lower(add_information.skills) LIKE lower(concat('%', :skill, '%')) " +
            "                        OR lower(add_information.courses_and_trainings) LIKE lower(concat('%', :skill, '%'))) as a " +
            "                    ON p.add_information_id = a.id " +
            "         INNER JOIN (SELECT education.person_data_id " +
            "                     FROM education" +
            "                              INNER JOIN (SELECT specification.id " +
            "                                          FROM specification " +
            "                                          WHERE lower(specification.educational_institution) LIKE " +
            "                                                lower(concat('%', :university, '%')) AND" +
            "                                                lower(specification.direction) LIKE " +
            "                                                lower(concat('%', :specialties, '%'))) as s " +
            "                                         ON education.specification_id = s.id) as e " +
            "                    ON p.id = e.person_data_id", nativeQuery = true)
    Integer getSkillsSpecialties(@Param("university") String university,
                                 @Param("specialties") String specialties,
                                 @Param("skill") String skill);

    @Query(value = "SELECT count(*) " +
            "FROM personal_data as p " +
            "         INNER JOIN (SELECT id " +
            "                     FROM add_information " +
            "                     where lower(add_information.about_me) LIKE lower(concat('%', :skill, '%')) " +
            "                        OR lower(add_information.skills) LIKE lower(concat('%', :skill, '%')) " +
            "                        OR lower(add_information.courses_and_trainings) LIKE lower(concat('%', :skill, '%'))) as a " +
            "                    ON p.add_information_id = a.id " +
            "         INNER JOIN (SELECT education.person_data_id " +
            "                     FROM education" +
            "                              INNER JOIN (SELECT specification.id " +
            "                                          FROM specification " +
            "                                          WHERE lower(specification.educational_institution) LIKE " +
            "                                                lower(concat('%', :university, '%')) AND" +
            "                                                lower(specification.direction) LIKE " +
            "                                                lower(concat('%', :specialties, '%')) AND" +
            "                                                specification.ending = :year) as s " +
            "                                         ON education.specification_id = s.id) as e " +
            "                    ON p.id = e.person_data_id", nativeQuery = true)
    Integer getSkillsSpecialtiesYear(@Param("university") String university,
                                     @Param("specialties") String specialties,
                                     @Param("year") Integer year,
                                     @Param("skill") String skill);

    //  Integer getSpecialtiesYearForeignLanguage();
}
