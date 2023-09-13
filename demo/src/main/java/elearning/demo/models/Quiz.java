package elearning.demo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Quiz {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    private String difficulty;
    private String description;
    private String totalPoints;
    private String duration;
    private String imageUrl;

    @OneToMany(mappedBy = "quiz")
    private List<Questions> questions;

}
