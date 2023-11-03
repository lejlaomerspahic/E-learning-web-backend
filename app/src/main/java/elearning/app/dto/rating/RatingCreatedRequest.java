package elearning.app.dto.rating;

import lombok.Data;

@Data
public class RatingCreatedRequest {

    private Long course;
    private Long product;
    private Long user;
    private int rating;
}
