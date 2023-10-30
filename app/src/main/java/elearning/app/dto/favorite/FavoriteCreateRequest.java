package elearning.app.dto.favorite;

import java.util.List;

import elearning.app.model.Course;
import elearning.app.model.Product;
import elearning.app.model.User;
import lombok.Data;

@Data
public class FavoriteCreateRequest {

    private User user;

    private List<Course> course;

    private List<Product> product;
}
