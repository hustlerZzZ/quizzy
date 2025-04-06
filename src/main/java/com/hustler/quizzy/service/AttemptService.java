package com.hustler.quizzy.service;

import com.hustler.quizzy.entity.*;
import com.hustler.quizzy.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class AttemptService {
    @Autowired
    private AttemptRepository attemptRepo;

    @Autowired
    private QuizRepository quizRepo;

    @Autowired
    private UserRepository userRepo;

    public Attempt submitAttempt(Long quizId, Long studentId, String ip, List<String> answers) {
        if (attemptRepo.findByQuizIdAndIp(quizId, ip).isPresent()) {
            throw new RuntimeException("You have already attempted this quiz from this IP.");
        }

        Quiz quiz = quizRepo.findById(quizId).orElseThrow();
        User student = userRepo.findById(studentId).orElseThrow();

        List<Question> questions = quiz.getQuestions();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (i < answers.size() && questions.get(i).getCorrectAnswer().equalsIgnoreCase(answers.get(i))) {
                score++;
            }
        }

        Attempt attempt = new Attempt(null, ip, score, student, quiz);
        return attemptRepo.save(attempt);
    }

    public List<Attempt> getResultsByQuiz(Long quizId) {
        return attemptRepo.findByQuizId(quizId);
    }
}
