package elearning.demo.models;

import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Questions {

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;
    private String questionText;
    private List<String> options;
    private Long correctOptionIndex;
}
