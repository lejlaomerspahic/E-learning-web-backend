package elearning.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.quiz.QuizCreatedRequest;
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
}
