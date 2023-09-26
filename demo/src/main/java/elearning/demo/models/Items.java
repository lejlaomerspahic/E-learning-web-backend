package elearning.demo.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Items {

    public Items(Product productId, Long count) {
        this.productId = productId;
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    private Long count;
}
