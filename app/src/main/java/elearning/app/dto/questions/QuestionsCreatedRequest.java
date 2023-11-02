package elearning.app.dto.questions;

import java.util.List;

import lombok.Data;

@Data
public class QuestionsCreatedRequest {
    private Long quiz;
    private String questionText;
    private List<String> options;
    private Long correctOptionIndex;
}
