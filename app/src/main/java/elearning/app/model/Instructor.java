package elearning.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Instructor {

    @Id
    @GeneratedValue()
    private Long id;
    private String bio;

    @ManyToOne
    private Contact contact;

    private String imageUrl;

    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses;
}