package com.hustler.quizzy.service;

import java.util.*;

import com.hustler.quizzy.entity.Quiz;
import com.hustler.quizzy.entity.Question;
import org.springframework.stereotype.Service;
import com.hustler.quizzy.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepo;

    public Quiz createQuiz(Quiz quiz) {
        quiz.setCode(UUID.randomUUID().toString().substring(0, 6));
        for (Question q : quiz.getQuestions()) {
            q.setQuiz(quiz);
        }
        return quizRepo.save(quiz);
    }

    public Optional<Quiz> getQuizByCode(String code) {
        return quizRepo.findByCode(code);
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepo.findById(id);
    }


    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }
}
