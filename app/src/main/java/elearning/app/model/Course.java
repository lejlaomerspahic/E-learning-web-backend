package elearning.app.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private String category;
    private String info;
    private String description;
    private String duration;
    private String level;
    private String imageUrl;
    private String videoId;
    private Date lastUpdated;
    private String language;
    private String icon;
    @ManyToMany
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "course")
    private List<Rating> ratings;
}