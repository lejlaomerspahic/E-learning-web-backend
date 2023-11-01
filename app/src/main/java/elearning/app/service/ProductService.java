package elearning.app.service;

import java.util.List;
import java.util.Optional;

import elearning.app.dto.product.ProductCreateRequest;
import elearning.app.model.Product;

public interface ProductService {

    Product create(ProductCreateRequest courseCreatedRequest);

    Optional<Product> getProduct(Long id);

    List<Product> get();

}