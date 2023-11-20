package elearning.app.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId;
    private Double amount;
    private String currency;

    private Date creationDate;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions_product", joinColumns = @JoinColumn(name = "transactions_id"),
            inverseJoinColumns = @JoinColumn(name = "productInfo_id"))
    private List<ProductInfo> productInfo;

}
