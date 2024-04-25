package com.example.serverparsing.controller;

import com.example.serverparsing.servise.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

// Решил использовать что-то новое и возвращать коллекцию для читаймости кода
// Сделайю обработку ошибку, если данного элемента нет
@Controller
@RequiredArgsConstructor
@RequestMapping("resume-api/analytics")
public class AnalyticsRestController {
    private final PersonalDataService personalDataService;

    @GetMapping("/skills-university")// Написал запрос
    public ResponseEntity<Map<String, Integer>> getSkillsUniversity(@RequestParam(value = "university", required = false) String university,
                                                                    @RequestParam(value = "skills", required = false) List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.analyticSkillsUniversity(university, skills));
    }

    @GetMapping("/skills-specialties")
    // Написал запрос
    public ResponseEntity<Map<String, Integer>> getSkillsSpecialties(@RequestParam(value = "university", required = false) String university,
                                                                     @RequestParam(value = "specialties", required = false) String specialties,
                                                                     @RequestParam(value = "skills", required = false) List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.analyticSkillsSpecialties(university, specialties, skills));
    }

    @GetMapping("/skills-specialties-year")
        // Написал запрос
    Map<String, Integer> getSkillsSpecialtiesYear(@RequestParam(value = "university", required = false) String university,
                                                  @RequestParam(value = "specialties", required = false) String specialties,
                                                  @RequestParam(value = "year", required = false) Integer year,
                                                  @RequestParam(value = "skills", required = false) List<String> skills) {
        return null;
    }

    /*
     * */
    @GetMapping("/specialties-year-foreign-language")
    Map<String, Integer> getSpecialtiesYearForeignLanguage(@RequestParam(value = "university", required = false) String university,
                                                           @RequestParam(value = "specialties", required = false) String specialties,
                                                           @RequestParam(value = "year", required = false) Integer year) {
        return null;
    }

    /*
     * */
    @GetMapping("/specialties-year-foreign-language-level")
    Map<String, Integer> getForeignLanguageLevel(@RequestParam(value = "university", required = false) String university,
                                                 @RequestParam(value = "specialties", required = false) String specialties,
                                                 @RequestParam(value = "year", required = false) Integer year,
                                                 @RequestParam(value = "foreignLanguage", required = false) List<String> foreignLanguage) {
        return null;
    }
}