package com.hustler.quizzy.repository;

import java.util.*;
import com.hustler.quizzy.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    Optional<Attempt> findByQuizIdAndIp(Long quizId, String ip);
    List<Attempt> findByQuizId(Long quizId);
}
