package elearning.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import elearning.demo.dto.quiz.QuizCreatedRequest;
import elearning.demo.models.Quiz;

@Service
public interface QuizService {

    String create(QuizCreatedRequest quizCreatedRequest);

    List<Quiz> search(String key);

    Quiz getQuiz(Long id);

}
