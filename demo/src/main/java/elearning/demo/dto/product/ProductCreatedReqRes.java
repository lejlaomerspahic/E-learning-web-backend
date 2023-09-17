package elearning.demo.dto.product;

public class ProductCreatedReqRes {

    private String title;

    private String supplier;

    private String price;

    private String imageUrl;

    private String description;

    private String productLocation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public ProductCreatedReqRes(String title, String supplier, String price, String imageUrl, String description, String productLocation) {
        super();
        this.title = title;
        this.supplier = supplier;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.productLocation = productLocation;
    }

}
