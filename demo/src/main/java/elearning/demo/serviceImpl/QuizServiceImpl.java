package elearning.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import elearning.demo.dto.quiz.QuizCreatedRequest;
import elearning.demo.mapper.QuizMapper;
import elearning.demo.models.Quiz;
import elearning.demo.repository.QuizRepository;
import elearning.demo.service.QuizService;

public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizMapper quizMapper;

    @Override
    public String create(QuizCreatedRequest quizCreatedRequest) {
        quizRepository.save(quizMapper.dtoToEntity(quizCreatedRequest));
        return "Quiz created successfully";
    }

    @Override
    public List<Quiz> search(String key) {
        return quizRepository.findByCategory(key);
    }

    @Override
    public Quiz getQuiz(Long id) {
        return quizRepository.findById(id).get();
    }

}
