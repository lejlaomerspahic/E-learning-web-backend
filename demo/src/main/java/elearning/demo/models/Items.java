package elearning.demo.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Items {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    private Long count;

    public Items() {
        super();
        this.productId = productId;
        this.count = count;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
