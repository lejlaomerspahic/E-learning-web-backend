package elearning.app.service;

import java.util.List;
import java.util.Optional;

import elearning.app.dto.quiz.QuizCreatedRequest;
import elearning.app.model.Quiz;

public interface QuizService {

    Quiz create(QuizCreatedRequest quizCreatedRequest);

    List<Quiz> search(String key);

    Optional<Quiz> getQuiz(Long id);

    List<Quiz> searchQuiz(String key);

    List<Quiz> get();

}