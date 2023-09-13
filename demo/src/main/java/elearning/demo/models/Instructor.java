package elearning.demo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String workingMode;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double hourlyRate;

    private String occupation;

    private List<String> subjects;

    private String bio;

    private Contact contact;

    private String imageUrl;

    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses;

}
