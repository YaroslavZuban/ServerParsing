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
@Table(name = "add_information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_information_seq")
    @SequenceGenerator(name = "add_information_seq", sequenceName = "add_information_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "courses_and_trainings")
    private String courses;

    @Column(name = "skills")
    private String skills;

    @Column(name = "about_me")
    private String aboutMe;

    @OneToMany
    @JoinTable(name = "languages_information",
            joinColumns = @JoinColumn(name = "information_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    @OneToMany
    @JoinTable(name = "levels_information",
            joinColumns = @JoinColumn(name = "information_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id"))
    private List<Level> levels;

    @ManyToOne
    @JoinColumn(name = "business_trips_id", referencedColumnName = "id")
    private BusinessTrips businessTrips;

    @OneToMany
    @JoinTable(name = "license",
            joinColumns = @JoinColumn(name = "add_information_id"),
            inverseJoinColumns = @JoinColumn(name = "license_category_id"))
    private List<LicenceCategory> categoryList;

    @OneToMany(mappedBy = "information")
    @JsonIgnore
    private List<PersonalData> personalDataList;

    public Information() {
    }

    public Information(String courses, String skills, String aboutMe) {
        this.courses = courses;
        this.skills = skills;
        this.aboutMe = aboutMe;
    }
}