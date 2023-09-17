package elearning.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;

@Service
public interface ProductService {
    Product getProduct(int id);

    List<Product> getProducts();

    ProductCreatedReqRes createProduct(ProductCreatedReqRes productCreatedReqRes);

}
