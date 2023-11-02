package elearning.app.dto.quiz;

import lombok.Data;

@Data
public class QuizCreatedRequest {
    private String name;
    private String category;
    private String difficulty;
    private String description;
    private String totalPoints;
    private String duration;
    private String imageUrl;
}
