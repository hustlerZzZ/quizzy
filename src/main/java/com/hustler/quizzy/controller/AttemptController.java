package com.hustler.quizzy.controller;

import com.hustler.quizzy.entity.Attempt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hustler.quizzy.service.AttemptService;

import java.util.List;

@RestController
@RequestMapping("/api/attempt")
@RequiredArgsConstructor
public class AttemptController {
    private final AttemptService attemptService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitAttempt(
            @RequestParam Long quizId,
            @RequestParam Long studentId,
            @RequestParam String ip,
            @RequestBody List<String> answers) {

        try {
            Attempt attempt = attemptService.submitAttempt(quizId, studentId, ip, answers);
            return ResponseEntity.ok(attempt);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Get all attempts by quiz (for teacher)
    @GetMapping("/results/{quizId}")
    public ResponseEntity<List<Attempt>> getQuizResults(@PathVariable Long quizId) {
        return ResponseEntity.ok(attemptService.getResultsByQuiz(quizId));
    }
}
