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
    public ResponseEntity<Map<String, Integer>> getSkillsUniversity(@RequestParam(value = "university") String university,
                                                                    @RequestParam(value = "skills") List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.analyticSkillsUniversity(university, skills));
    }

    @GetMapping("/skills-specialties")
    // Написал запрос
    public ResponseEntity<Map<String, Integer>> getSkillsSpecialties(@RequestParam(value = "university") String university,
                                                                     @RequestParam(value = "specialties") String specialties,
                                                                     @RequestParam(value = "skills") List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.analyticSkillsSpecialties(university, specialties, skills));
    }

    @GetMapping("/skills-specialties-year")
    // Написал запрос
    public ResponseEntity<Map<String, Integer>> getSkillsSpecialtiesYear(@RequestParam(value = "university") String university,
                                                                         @RequestParam(value = "specialties") String specialties,
                                                                         @RequestParam(value = "year") Integer year,
                                                                         @RequestParam(value = "skills") List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.analyticSkillsSpecialtiesYear(university, specialties, year, skills));
    }
}