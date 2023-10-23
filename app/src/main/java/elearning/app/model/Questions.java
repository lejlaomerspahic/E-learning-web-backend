package elearning.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Questions {
    @Id
    @GeneratedValue()
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;
    private String questionText;
    private List<String> options;
    private Long correctOptionIndex;
}
