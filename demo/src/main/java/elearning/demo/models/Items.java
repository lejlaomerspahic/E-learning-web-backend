package elearning.demo.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Items {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    private Long count;
}
