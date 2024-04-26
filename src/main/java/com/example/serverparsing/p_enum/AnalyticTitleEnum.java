package com.example.serverparsing.p_enum;

import lombok.Getter;

@Getter
public enum AnalyticTitleEnum {
    ANALYTIC_TITLE_SKILLS_UNIVERSITY("Аналитика навыков в университете", "Аналитика универ.xlsx"),
    ANALYTIC_TITLE_SKILLS_SPECIALTIES("Аналитика навыков в университете по специальности", "Аналитика спец.xlsx"),
    ANALYTIC_TITLE_SKILLS_SPECIALTIES_YEAR("Аналитика навыков в университете определенной специальности в году", "Аналитика спец_год.xlsx");

    private final String title;
    private final String fileName;

    AnalyticTitleEnum(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
    }

}