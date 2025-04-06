package com.hustler.quizzy.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private int score;

    @ManyToOne
    private User student;

    @ManyToOne
    private Quiz quiz;
}
