package elearning.demo.dto.product;

import elearning.demo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailsResponse {

    private Product product;
    private Long userRating;
    private int allRatings;
    private double averageRating;
}
