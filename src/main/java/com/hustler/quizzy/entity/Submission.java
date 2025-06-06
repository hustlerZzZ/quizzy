package com.hustler.quizzy.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@Getter
@Setter
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
