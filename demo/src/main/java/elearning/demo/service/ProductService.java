package elearning.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import elearning.demo.dto.course.RatingRequest;
import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;

@Service
public interface ProductService {

    List<Product> getProducts();

    String createProduct(ProductCreatedReqRes productCreatedReqRes);

    Product getProduct(Long id);

    List<Product> search(String key);

    Double rating(Long productId, RatingRequest ratingRequest, Long userId);

    ResponseEntity<Object> check(Long productId, Long userId);

}
