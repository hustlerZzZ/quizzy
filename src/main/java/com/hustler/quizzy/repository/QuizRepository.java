package com.hustler.quizzy.repository;

import java.util.Optional;
import com.hustler.quizzy.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public default Optional<Quiz> findByCode(String code) {
        return Optional.empty();
    }
}
