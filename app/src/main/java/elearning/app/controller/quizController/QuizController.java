package elearning.app.controller.quizController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.quiz.QuizCreatedRequest;
import elearning.app.model.Quiz;
import elearning.app.service.QuizService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:3000")

public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public Quiz createQuiz(@RequestBody QuizCreatedRequest courseCreatedRequest) throws Exception {

        return quizService.create(courseCreatedRequest);
    }

    @GetMapping("/search/{key}")
    public List<Quiz> searchQuiz(@PathVariable String key) {
        return quizService.search(key);

    }

    @GetMapping("/search/info/{key}")
    public List<Quiz> searchQuizInput(@PathVariable String key) {
        return quizService.searchQuiz(key);

    }

    @GetMapping("/get")
    public List<Quiz> searchQuizAll() {
        return quizService.get();

    }

    @GetMapping("/{id}")
    public Optional<Quiz> getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }

}