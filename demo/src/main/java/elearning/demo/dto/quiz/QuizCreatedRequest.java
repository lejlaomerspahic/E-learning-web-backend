package elearning.demo.dto.quiz;

import java.util.List;

import elearning.demo.models.Questions;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class QuizCreatedRequest {
    private Long id;

    private String name;

    private String category;

    private String difficulty;
    private String description;
    private String totalPoints;
    private String duration;
    private String imageUrl;

    @OneToMany(mappedBy = "quiz")
    private List<Questions> questions;
}
