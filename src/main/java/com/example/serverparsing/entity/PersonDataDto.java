package com.example.serverparsing.entity;

import com.example.serverparsing.entity.CitizenshipType;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.List;

public record PersonDataDto(
        @NotNull
        int id,
        @NotNull
        String name,
        String title,
        Integer wages,
        String skills) {
}
