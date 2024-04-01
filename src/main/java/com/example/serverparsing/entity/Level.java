package com.example.serverparsing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_seq")
    @SequenceGenerator(name = "language_seq", sequenceName = "language_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "knowledge_level")
    private String knowledgeLevel;

    public Level() {
    }

    public Level(String knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }
}
