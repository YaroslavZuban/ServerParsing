package com.example.serverparsing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employees_experience")
public class EmployeesExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_experience_seq")
    @SequenceGenerator(name = "employees_experience_seq", sequenceName = "employees_experience_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "post")
    private String post;

    @Column(name = "responsibilities")
    private String responsibilities; //Обязанности

    @Column(name = "company")
    private String company;

    @ManyToMany(mappedBy = "experienceList")
    @JsonIgnore
    private List<PersonalData> personalDataList;

    public EmployeesExperience() {
    }

    public EmployeesExperience(String post, String responsibilities, String company) {
        this.post = post;
        this.responsibilities = responsibilities;
        this.company = company;
    }
}