package elearning.demo.dto.user;

import java.util.List;

public class UserUpadateProducts {
    private List<Long> productIds;
    private String date;
    private List<Long> counts;
    private Long price;
    private String status;
    private List<String> places;
    private Long priceForDifferentLocation;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Long> getCounts() {
        return counts;
    }

    public void setCounts(List<Long> counts) {
        this.counts = counts;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    public Long getPriceForDifferentLocation() {
        return priceForDifferentLocation;
    }

    public void setPriceForDifferentLocation(Long priceForDifferentLocation) {
        this.priceForDifferentLocation = priceForDifferentLocation;
    }

}
