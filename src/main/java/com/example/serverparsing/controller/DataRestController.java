package com.example.serverparsing.controller;

import com.example.serverparsing.servise.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("resume-api/data")
public class DataRestController {
    private final LanguageService languageService;
    private final LanguageLevelService levelService;
    private final HabitationService habitationService;
    private final WorkTypeService workTypeService;
    private final LicenceCategoryService licenceCategoryService;
    private final GenderService genderService;
    private final EducationLevelService educationLevelService;
    private final CitizenshipService citizenshipService;

    @GetMapping("/language")
    public ResponseEntity<?> getLanguageAll() {
        return ResponseEntity.ok().body(languageService.getLanguageList());
    }

    @GetMapping("/language-level")
    public ResponseEntity<?> getLanguageLevel() {
        return ResponseEntity.ok().body(levelService.getLanguageLevelAll());
    }

    @GetMapping("/city")
    public ResponseEntity<?> getCityAll() {
        return ResponseEntity.ok().body(habitationService.getCityAll());
    }

    @GetMapping("/work-type")
    public ResponseEntity<?> getWortTypeAll() {
        return ResponseEntity.ok().body(workTypeService.getWortTypeAll());
    }

    @GetMapping("/licence-category")
    public ResponseEntity<?> getLicenceCategoryAll() {
        return ResponseEntity.ok().body(licenceCategoryService.getLicenceCategoryAll());
    }

    @GetMapping("/gender")
    public ResponseEntity<?> getGenderAll() {
        return ResponseEntity.ok().body(genderService.getGenderAll());
    }

    @GetMapping("/education-level")
    public ResponseEntity<?> getEducationLevelAll() {
        return ResponseEntity.ok().body(educationLevelService.getEducationLevelAll());
    }

    @GetMapping("/citizenship")
    public ResponseEntity<?> getCitizenshipAll() {
        return ResponseEntity.ok().body(citizenshipService.getCitizenshipAll());
    }
}
