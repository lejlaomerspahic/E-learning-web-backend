package elearning.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import elearning.app.dto.quiz.QuizCreatedRequest;
import elearning.app.model.Quiz;
import elearning.app.repository.QuizRepository;
import elearning.app.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public Quiz create(QuizCreatedRequest courseCreatedRequest) {
        Quiz quiz = new Quiz();
        quiz.setName(courseCreatedRequest.getName());
        quiz.setCategory(courseCreatedRequest.getCategory());
        quiz.setDescription(courseCreatedRequest.getDescription());
        quiz.setDifficulty(courseCreatedRequest.getDifficulty());
        quiz.setDuration(courseCreatedRequest.getDuration());
        quiz.setImageUrl(courseCreatedRequest.getImageUrl());
        quiz.setTotalPoints(courseCreatedRequest.getTotalPoints());
        return quizRepository.save(quiz);

    }

    @Override
    public List<Quiz> search(String key) {
        return quizRepository.findByCategory(key);
    }

    @Override
    public Optional<Quiz> getQuiz(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> searchQuiz(String key) {
        return quizRepository.findQuizzesBySearchString(key);
    }

    @Override
    public List<Quiz> get() {
        return quizRepository.findAll();
    }

}