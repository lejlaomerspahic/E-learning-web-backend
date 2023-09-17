package elearning.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;
import elearning.demo.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@CrossOrigin("*")
@Api(value = "Product", tags = "Product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_ROLE')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }

    @PostMapping("/postProduct")
    @PreAuthorize("hasAuthority('USER_ROLE')")
    public ProductCreatedReqRes postProduct(@RequestBody ProductCreatedReqRes productCreatedReqRes) {
        return service.createProduct(productCreatedReqRes);
    }
}
