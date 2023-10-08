package elearning.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.course.RatingRequest;
import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;
import elearning.demo.security.JwtService;
import elearning.demo.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin("*")
@Api(value = "Product", tags = "Product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/postProduct")
    @PreAuthorize("hasAuthority('USER_ROLE')")
    public String postProduct(@RequestBody ProductCreatedReqRes productCreatedReqRes) {
        return service.createProduct(productCreatedReqRes);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping("/get")
    public List<Product> getProduct() {
        return service.getProducts();
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<Object> searchProduct(@PathVariable String key) {
        List<Product> products = service.search(key);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{productId}/rating")
    public Double rating(@PathVariable Long productId, @RequestBody RatingRequest ratingRequest,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        return service.rating(productId, ratingRequest, userId);

    }
}
