package com.example.serverparsing.entity;

import jakarta.validation.constraints.NotNull;

public record PersonDataDto(
        @NotNull
        int id,
        @NotNull
        String name,
        String title,
        Integer wages,
        String skills) {
}
