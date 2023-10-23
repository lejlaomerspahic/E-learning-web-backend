package elearning.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Instructor {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;
    private String bio;

    @ManyToOne
    private Contact contact;

    private String imageUrl;
    @ManyToMany
    @JoinTable(name = "instructor_course", joinColumns = @JoinColumn(name = "instructors_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Course> course;
}