package elearning.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Favorite {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "favorite")
    private List<Course> course;

    @OneToMany(mappedBy = "favorite")
    private List<Product> product;
}
