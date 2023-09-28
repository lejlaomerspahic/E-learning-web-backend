package elearning.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;

@Service
public interface ProductService {

    List<Product> getProducts();

    String createProduct(ProductCreatedReqRes productCreatedReqRes);

    Product getProduct(Long id);

    List<Product> search(String key);

}
