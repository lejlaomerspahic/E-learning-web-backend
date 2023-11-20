package elearning.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class ProductInfo {
    @Id
    @GeneratedValue()
    private Long id;
    private Long productId;
    private Integer count;

    @ManyToMany(mappedBy = "productInfo", fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Transactions> transactions;
}
