package elearning.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue()
    private Long id;
    //
    // @ManyToOne
    // private User user;
    //
    // @OneToMany(mappedBy = "favorite")
    // private List<Course> course;
    //
    // @OneToMany(mappedBy = "favorite")
    // private List<Product> product;
}
