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
@Table(name = "specification")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specification_seq")
    @SequenceGenerator(name = "specification_seq", sequenceName = "specification_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "ending")
    private int ending;

    @Column(name = "educational_institution")
    private String educationalInstitution;

    @Column(name = "direction")
    private String direction;

    @ManyToOne
    @JoinColumn(name = "education_type_id", referencedColumnName = "id")
    private EducationType educationType;

    @ManyToMany(mappedBy = "specifications")
    @JsonIgnore
    private List<PersonalData> personalData;

    public Specification() {
    }

    public Specification(int ending, String educationalInstitution, String direction, EducationType educationType) {
        this.ending = ending;
        this.educationalInstitution = educationalInstitution;
        this.direction = direction;
        this.educationType = educationType;
    }
}