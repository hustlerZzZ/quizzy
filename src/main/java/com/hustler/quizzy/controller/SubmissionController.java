package com.hustler.quizzy.controller;

import com.hustler.quizzy.entity.*;
import com.hustler.quizzy.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Long studentId,
                             @RequestParam String quizCode,
                             @RequestParam Integer score,
                             HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Quiz quiz = quizRepository.findByCode(quizCode).orElseThrow();
        User student = userRepository.findById(studentId).orElseThrow();

        if (submissionRepository.findByQuizIdAndIpAdress(quiz.getId(), ip).isPresent()) {
            return "You already have this quiz";
        }

        Submission submission = new Submission(null, ip, score, quiz, student);
        submissionRepository.save(submission);
        return "You submitted the quiz";
    }
}
