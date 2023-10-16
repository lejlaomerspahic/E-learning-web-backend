package elearning.app.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue()
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    private String info;
    private String description;
    private String duration;
    private String level;
    private String imageUrl;
    private String videoId;
    private Date lastUpdated;
    private String language;
    @ManyToMany
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "course")
    private List<Rating> ratings;
}