package com.example.serverparsing.controller;

import com.example.serverparsing.entity.PersonalData;
import com.example.serverparsing.servise.PersonalDataService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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

    @GetMapping
    public ResponseEntity<?> findPersonalDataAll(@RequestParam(defaultValue = "1") int pageNumber) {
        if (pageNumber < 1) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Invalid pageNumber: " + pageNumber);
        }

        List<PersonalData> list = personalDataService.getListPersonalDataAll(pageNumber);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{resumeId:\\d+}")
    public ResponseEntity<?> findPersonData(@PathVariable(value = "resumeId") int resumeId) {
        PersonalData personalData = personalDataService.getPersonDataId(resumeId)
                .orElseThrow(() -> new NoSuchElementException("Данного резюме нет"));

        return ResponseEntity.ok().body(personalData);
    }
}
