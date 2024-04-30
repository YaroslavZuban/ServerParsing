package com.example.serverparsing.p_enum;

import lombok.Getter;

@Getter
public enum AnalyticTitleEnum {
    ANALYTIC_TITLE_SKILLS_UNIVERSITY("Analytics-university.xlsx"),
    ANALYTIC_TITLE_SKILLS_SPECIALTIES("Analytics-spec.xlsx"),
    ANALYTIC_TITLE_SKILLS_SPECIALTIES_YEAR("Analytics-spec_year.xlsx");


    private final String fileName;

    AnalyticTitleEnum(String fileName) {

        this.fileName = fileName;
    }

}