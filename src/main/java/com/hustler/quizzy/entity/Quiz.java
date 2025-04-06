package com.hustler.quizzy.entity;

import lombok.*;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Quiz(Long id, String title, String code, User teacher) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.teacher = teacher;
    }

    private String title;

    @Column(unique = true)
    private String code;

    @ManyToOne
    private User teacher;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;
}
