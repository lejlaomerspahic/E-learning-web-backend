package elearning.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questions {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;
    private String questionText;
    private List<String> options;
    private Long correctOptionIndex;
}
