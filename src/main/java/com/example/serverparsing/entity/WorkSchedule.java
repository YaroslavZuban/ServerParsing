package com.example.serverparsing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "work_schedule")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_schedule_seq")
    @SequenceGenerator(name = "work_schedule_seq", sequenceName = "work_schedule_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "work_type")
    private String workType;

    @ManyToMany(mappedBy = "workSchedule")
    @JsonIgnore
    private List<PersonalData> personalData;

    public WorkSchedule() {
    }

    public WorkSchedule(String workType) {
        this.workType = workType;
    }
}
