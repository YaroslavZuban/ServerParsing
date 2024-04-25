package com.example.serverparsing.servise;

import com.example.serverparsing.entity.PersonDataDto;
import com.example.serverparsing.entity.PersonalData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonalDataService {

    Optional<PersonalData> getPersonDataId(int personId);

    List<PersonDataDto> findAllPersonData(String cityResidence,
                                          Integer wages,
                                          String foreignLanguage,
                                          String foreignLanguageLevel,
                                          List<String> educations,
                                          String gender,
                                          List<String> workSchedules,
                                          List<String> rightsCategory,
                                          List<String> businessTrips,
                                          String educationalInstitution,
                                          String specialization,
                                          Integer graduationYear,
                                          List<String> skills,
                                          List<String> citizenship,
                                          String educationLevel,
                                          int pageNumber);

    Map<String, Integer> analyticSkillsUniversity(String university, List<String> skills);

    Map<String, Integer> analyticSkillsSpecialties(String university, String specialties, List<String> skills);
}