package com.hustler.quizzy.controller;

import java.util.Optional;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import com.hustler.quizzy.entity.User;
import com.hustler.quizzy.entity.Quiz;
import com.hustler.quizzy.service.UserService;
import com.hustler.quizzy.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class ViewController {
    private final UserService userService;
    private final QuizService quizService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/view/login";
    }

    @GetMapping("/quiz-code")
    public String showQuizCodePage() {
        return "quiz-code";
    }

    @GetMapping("/quiz/join")
    public String joinQuiz(@RequestParam String code, Model model) {
        Optional<Quiz> quizOpt = quizService.getQuizByCode(code);
        if (quizOpt.isEmpty()) {
            model.addAttribute("error", "Invalid quiz code.");
            return "quiz-code";
        }
        model.addAttribute("quiz", quizOpt.get());
        return "take-quiz";
    }
}
