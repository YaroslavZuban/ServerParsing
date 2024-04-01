package com.example.serverparsing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_data_seq")
    @SequenceGenerator(name = "personal_data_seq", sequenceName = "personal_data_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "wages")
    private Integer wages;

    @ManyToOne
    @JoinColumn(name = "habitation_id", referencedColumnName = "id")
    private Habitation habitation;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private Gender gender;

    @Column(name = "birth_data")
    @Temporal(TemporalType.DATE)
    private Date birthData;

    @ManyToOne
    @JoinColumn(name = "add_information_id", referencedColumnName = "id")
    private Information information;

    @ManyToMany
    @JoinTable(name = "work",
            joinColumns = @JoinColumn(name = "person_data_id"),
            inverseJoinColumns = @JoinColumn(name = "employees_experience_id"))
    private List<EmployeesExperience> experienceList;

    @ManyToMany
    @JoinTable(name = "employment_type",
            joinColumns = @JoinColumn(name = "person_data_id"),
            inverseJoinColumns = @JoinColumn(name = "work_schedule_id"))
    private List<WorkSchedule> workSchedule;

    @ManyToMany
    @JoinTable(name = "education",
            joinColumns = @JoinColumn(name = "person_data_id"),
            inverseJoinColumns = @JoinColumn(name = "specification_id"))
    private List<Specification> specifications;

    @ManyToMany
    @JoinTable(name = "citizenship",
            joinColumns = @JoinColumn(name = "person_data_id"),
            inverseJoinColumns = @JoinColumn(name = "citizenship_type_id"))
    private List<CitizenshipType> citizenshipType;

    public PersonalData() {
    }

    public PersonalData(String name, String title, Integer wages, Date birthData) {
        this.name = name;
        this.title = title;
        this.wages = wages;
        this.birthData = birthData;
    }
}
