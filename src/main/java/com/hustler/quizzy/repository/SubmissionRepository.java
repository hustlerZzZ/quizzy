package com.hustler.quizzy.repository;

import java.util.Optional;

import com.hustler.quizzy.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    public default Optional<Submission> findByQuizIdAndIpAdress(Long quizId, String ipAddress) {
        return Optional.empty();
    }
}
