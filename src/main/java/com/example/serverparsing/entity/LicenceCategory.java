package com.example.serverparsing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "licence_category")
public class LicenceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licence_category_seq")
    @SequenceGenerator(name = "licence_category_seq", sequenceName = "licence_category_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "category")
    private String category;

    public LicenceCategory() {
    }

    public LicenceCategory(String category) {
        this.category = category;
    }
}