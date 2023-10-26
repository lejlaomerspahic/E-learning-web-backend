package elearning.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String workingMode;

    private String location;

    private Double hourlyRate;

    private String occupation;

    private List<String> subjects;

    private String bio;

    private String background;
    @ManyToOne
    private Contact contact;

    private String imageUrl;
    @ManyToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Course> course;

}