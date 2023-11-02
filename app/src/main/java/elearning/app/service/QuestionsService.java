package elearning.app.service;

import elearning.app.dto.questions.QuestionsCreatedRequest;
import elearning.app.model.Questions;

public interface QuestionsService {

    Questions create(QuestionsCreatedRequest questionCreatedRequest);

}