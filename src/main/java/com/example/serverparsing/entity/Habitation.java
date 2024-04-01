package com.example.serverparsing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "habitation")
public class Habitation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habitation_seq")
    @SequenceGenerator(name = "habitation_seq", sequenceName = "habitation_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "habitation")
    @JsonIgnore
    private List<PersonalData> personalData;

    public Habitation() {
    }

    public Habitation(String city) {
        this.city = city;
    }
}