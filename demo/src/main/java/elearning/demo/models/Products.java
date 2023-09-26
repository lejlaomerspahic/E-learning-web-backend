package elearning.demo.models;

import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Products {

    public Products(List<Items> items2, String date, String status, Long price, String place) {

    }

    @OneToMany(mappedBy = "products")
    private List<Items> items;

    private String date;
    private String status;
    private String price;
    private String place;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

}
