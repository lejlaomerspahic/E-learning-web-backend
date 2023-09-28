package elearning.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.quiz.QuizCreatedRequest;
import elearning.demo.models.Quiz;
import elearning.demo.service.QuizService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public String createCourse(@RequestBody QuizCreatedRequest quizCreatedRequest) throws Exception {
        return quizService.create(quizCreatedRequest);
    }

    @GetMapping("/search/course/{key}")
    public List<Quiz> searchQuiz(@PathVariable String key) {
        return quizService.search(key);
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }
}
