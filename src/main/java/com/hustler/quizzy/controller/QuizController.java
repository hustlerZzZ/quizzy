package com.hustler.quizzy.controller;

import com.hustler.quizzy.entity.Question;
import com.hustler.quizzy.entity.Quiz;
import com.hustler.quizzy.entity.User;
import com.hustler.quizzy.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hustler.quizzy.repository.UserRepository;
import com.hustler.quizzy.repository.QuizRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(
            @RequestParam Long teacherId,
            @RequestParam String title,
            @RequestParam String code) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!"TEACHER".equalsIgnoreCase(teacher.getRole())) {
            return ResponseEntity.badRequest().body("Only teachers can create quizzes.");
        }

        Quiz quiz = new Quiz(null, title, code, teacher);
        Quiz savedQuiz = quizRepository.save(quiz);

        return ResponseEntity.ok(savedQuiz);
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Long quizId,
                             @RequestParam Map<String, String> answers,
                             Model model) {

        Optional<Quiz> quizOpt = quizService.getQuizById(quizId);
        if (quizOpt.isEmpty()) {
            model.addAttribute("error", "Quiz not found.");
            return "error";
        }

        Quiz quiz = quizOpt.get();
        int score = 0;
        int total = quiz.getQuestions().size();

        for (Question question : quiz.getQuestions()) {
            String userAnswer = answers.get("answers[" + question.getId() + "]");
            if (userAnswer != null && userAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", total);
        return "result";
    }
}
