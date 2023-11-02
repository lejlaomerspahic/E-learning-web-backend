package elearning.app.controller.questionsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.questions.QuestionsCreatedRequest;
import elearning.app.model.Questions;
import elearning.app.service.QuestionsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:3000")

public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @PostMapping("/create")
    public Questions createCourse(@RequestBody QuestionsCreatedRequest questionsCreatedRequest) throws Exception {
        return questionsService.create(questionsCreatedRequest);
    }

}