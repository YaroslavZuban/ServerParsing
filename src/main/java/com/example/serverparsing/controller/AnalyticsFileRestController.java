package com.example.serverparsing.controller;

import com.aspose.cells.Workbook;
import com.example.serverparsing.graph_excel.GraphExcelFile;
import com.example.serverparsing.graph_excel.GraphExcelFileImpl;
import com.example.serverparsing.servise.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("resume-api/analytics-file")
public class AnalyticsFileRestController {
    private final PersonalDataService personalDataService;

    @GetMapping("/skills-university")
    public ResponseEntity<Workbook> getSkillsUniversity(@RequestParam(value = "university") String university,
                                                        @RequestParam(value = "skills") List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.graphExcelSkillsUniversity(university, skills));
    }

    @GetMapping("/skills-specialties")
    public ResponseEntity<Workbook> getSkillsSpecialties(@RequestParam(value = "university") String university,
                                                         @RequestParam(value = "specialties") String specialties,
                                                         @RequestParam(value = "skills") List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.graphExcelSkillsSpecialties(university, specialties, skills));
    }

    @GetMapping("/skills-specialties-year")
    public ResponseEntity<Workbook> getSkillsSpecialtiesYear(@RequestParam(value = "university") String university,
                                                             @RequestParam(value = "specialties") String specialties,
                                                             @RequestParam(value = "year") Integer year,
                                                             @RequestParam(value = "skills") List<String> skills) {
        return ResponseEntity.ok().body(this.personalDataService.graphExcelSkillsSpecialtiesYear(university, specialties, year, skills));
    }
}
