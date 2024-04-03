package com.example.serverparsing.servise;

import com.example.serverparsing.entity.PersonDataDto;
import com.example.serverparsing.entity.PersonalData;

import java.util.List;
import java.util.Optional;

public interface PersonalDataService {

    List<PersonDataDto> getListPersonalDataAll(int pageNumber);

    Optional<PersonalData> getPersonDataId(int personId);

    List<PersonalData> findAllPersonData(String cityResidence,
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
                                         String educationLevel);
}