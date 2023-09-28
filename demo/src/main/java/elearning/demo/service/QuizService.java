package elearning.demo.service;

import java.util.List;

import elearning.demo.dto.quiz.QuizCreatedRequest;
import elearning.demo.models.Quiz;

public interface QuizService {

    String create(QuizCreatedRequest quizCreatedRequest);

    List<Quiz> search(String key);

    Quiz getQuiz(Long id);

}
