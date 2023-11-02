package elearning.app.serviceImpl;

import org.springframework.stereotype.Service;

import elearning.app.dto.questions.QuestionsCreatedRequest;
import elearning.app.model.Questions;
import elearning.app.model.Quiz;
import elearning.app.repository.QuestionsRepository;
import elearning.app.repository.QuizRepository;
import elearning.app.service.QuestionsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsRepository questionsRepository;

    private final QuizRepository quizRepository;

    @Override
    public Questions create(QuestionsCreatedRequest questionsCreatedRequest) {
        Questions questions = new Questions();
        questions.setQuestionText(questionsCreatedRequest.getQuestionText());
        questions.setOptions(questionsCreatedRequest.getOptions());
        questions.setCorrectOptionIndex(questionsCreatedRequest.getCorrectOptionIndex());
        Quiz quiz = quizRepository.getById(questionsCreatedRequest.getQuiz());
        questions.setQuiz(quiz);
        return questionsRepository.save(questions);

    }

}