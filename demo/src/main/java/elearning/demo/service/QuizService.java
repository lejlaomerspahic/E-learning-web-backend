package elearning.demo.service;

import elearning.demo.dto.quiz.QuizCreatedRequest;

public interface QuizService {

    String create(QuizCreatedRequest quizCreatedRequest);

}
