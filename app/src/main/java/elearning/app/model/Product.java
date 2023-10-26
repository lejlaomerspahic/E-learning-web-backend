package elearning.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String supplier;
    @Column(nullable = false)
    private String price;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String productLocation;
    @OneToMany(mappedBy = "product")
    private List<Rating> ratings;
    //
    // @ManyToOne
    // private Favorite favorite;
}