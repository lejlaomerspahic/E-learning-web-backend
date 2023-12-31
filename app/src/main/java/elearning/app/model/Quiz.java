package elearning.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue()
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

    @OneToMany(mappedBy = "quiz")
    private List<QuizResult> quizResults;
}