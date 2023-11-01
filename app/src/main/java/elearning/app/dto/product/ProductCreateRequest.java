package elearning.app.dto.product;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String title;
    private String supplier;
    private String price;
    private String imageUrl;
    private String description;
    private String productLocation;
}
