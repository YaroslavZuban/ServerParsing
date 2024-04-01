package com.example.serverparsing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "education_type")
public class EducationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_type_seq")
    @SequenceGenerator(name = "education_type_seq", sequenceName = "education_type_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "education_level")
    private String educationLevel;

    @OneToMany(mappedBy = "educationType")
    @JsonIgnore
    private List<Specification> specification;

    public EducationType() {
    }

    public EducationType(String educationLevel) {
        this.educationLevel = educationLevel;
    }

}
