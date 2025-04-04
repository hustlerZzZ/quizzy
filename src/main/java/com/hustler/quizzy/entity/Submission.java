package com.hustler.quizzy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip_address;
    private Integer score;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private User student;
}
