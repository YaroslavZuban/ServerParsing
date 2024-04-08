package com.example.serverparsing.controller;

import com.example.serverparsing.entity.PersonDataDto;
import com.example.serverparsing.entity.PersonalData;
import com.example.serverparsing.servise.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("resume-api/resume")
public class ResumeRestController {
    private final PersonalDataService personalDataService;

/*    @GetMapping
    public ResponseEntity<?> findPersonalDataAll(@RequestParam(defaultValue = "1") int pageNumber) {
        if (pageNumber < 1) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Invalid pageNumber: " + pageNumber);
        }

        List<PersonDataDto> list = personalDataService.getListPersonalDataAll(pageNumber);
        return ResponseEntity.ok().body(list);
    }*/

    @GetMapping
    public ResponseEntity<?> filterPersonData(@RequestParam(value = "cityResidence", required = false) String cityResidence,
                                              @RequestParam(value = "wages", required = false) Integer wages,
                                              @RequestParam(value = "foreignLanguage", required = false) String foreignLanguage,
                                              @RequestParam(value = "foreignLanguageLevel", required = false) String foreignLanguageLevel,
                                              @RequestParam(value = "educations", required = false) List<String> educations,
                                              @RequestParam(value = "gender", required = false) String gender,
                                              @RequestParam(value = "workSchedules", required = false) List<String> workSchedules,
                                              @RequestParam(value = "rightsCategory", required = false) List<String> rightsCategory,
                                              @RequestParam(value = "businessTrips", required = false) List<String> businessTrips,
                                              @RequestParam(value = "educationalInstitution", required = false) String educationalInstitution,
                                              @RequestParam(value = "specialization", required = false) String specialization,
                                              @RequestParam(value = "graduationYear", required = false) Integer graduationYear,
                                              @RequestParam(value = "skills", required = false) List<String> skills,
                                              @RequestParam(value = "citizenship", required = false) List<String> citizenship,
                                              @RequestParam(value = "educationLevel", required = false) String educationLevel,
                                              @RequestParam(defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(this.personalDataService.findAllPersonData(cityResidence,
                wages,
                foreignLanguage,
                foreignLanguageLevel,
                educations,
                gender,
                workSchedules,
                rightsCategory,
                businessTrips,
                educationalInstitution,
                specialization,
                graduationYear,
                skills,
                citizenship,
                educationLevel,
                pageNumber));
    }

    @GetMapping("/{resumeId:\\d+}")
    public ResponseEntity<?> findPersonData(@PathVariable(value = "resumeId") int resumeId) {
        PersonalData personalData = personalDataService.getPersonDataId(resumeId)
                .orElseThrow(() -> new NoSuchElementException("Данного резюме нет"));

        return ResponseEntity.ok().body(personalData);
    }
}
