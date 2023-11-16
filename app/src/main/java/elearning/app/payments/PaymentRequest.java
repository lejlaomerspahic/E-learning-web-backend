package elearning.app.payments;

import java.util.List;

import elearning.app.model.ProductInfo;
import lombok.Data;

@Data
public class PaymentRequest {

    private Double amount;
    private String currency;

    private List<ProductInfo> products;
}