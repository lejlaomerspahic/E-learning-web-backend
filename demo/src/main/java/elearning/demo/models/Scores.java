package elearning.demo.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Scores {

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quizId;
    private Long score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
