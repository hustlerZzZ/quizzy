package com.hustler.quizzy.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAnswer;

    @ManyToOne
    private Quiz quiz;

}
