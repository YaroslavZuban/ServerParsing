package com.example.serverparsing.controller;

import com.aspose.cells.Workbook;
import com.example.serverparsing.graph_excel.GraphExcelFile;
import com.example.serverparsing.graph_excel.GraphExcelFileImpl;
import com.example.serverparsing.p_enum.AnalyticTitleEnum;
import com.example.serverparsing.servise.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public ResponseEntity<Resource> getSkillsUniversity(@RequestParam(value = "university") String university,
                                                        @RequestParam(value = "skills") List<String> skills) {
        return getExcelFile(this.personalDataService.graphExcelSkillsUniversity(university, skills),
                AnalyticTitleEnum.ANALYTIC_TITLE_SKILLS_UNIVERSITY.getFileName());
    }

    @GetMapping("/skills-specialties")
    public ResponseEntity<Resource> getSkillsSpecialties(@RequestParam(value = "university") String university,
                                                         @RequestParam(value = "specialties") String specialties,
                                                         @RequestParam(value = "skills") List<String> skills) {
        return getExcelFile(this.personalDataService.graphExcelSkillsSpecialties(university, specialties, skills),
                AnalyticTitleEnum.ANALYTIC_TITLE_SKILLS_SPECIALTIES.getFileName());
    }

    @GetMapping("/skills-specialties-year")
    public ResponseEntity<Resource> getSkillsSpecialtiesYear(@RequestParam(value = "university") String university,
                                                             @RequestParam(value = "specialties") String specialties,
                                                             @RequestParam(value = "year") Integer year,
                                                             @RequestParam(value = "skills") List<String> skills) {
        return getExcelFile(this.personalDataService.graphExcelSkillsSpecialtiesYear(university, specialties, year, skills),
                AnalyticTitleEnum.ANALYTIC_TITLE_SKILLS_SPECIALTIES_YEAR.getFileName());
    }

    public ResponseEntity<Resource> getExcelFile(Resource resource, String nameFile) {
        String headerValue = String.format("attachment; filename=%s", nameFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);

        // ¬озвращаем ResponseEntity с ресурсом и заголовками
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
