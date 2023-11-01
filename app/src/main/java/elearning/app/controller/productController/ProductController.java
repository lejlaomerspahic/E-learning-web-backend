package elearning.app.controller.productController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.product.ProductCreateRequest;
import elearning.app.model.Product;
import elearning.app.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductCreateRequest productCreateRequest) throws Exception {
        return productService.create(productCreateRequest);
    }

    @GetMapping("/get")
    public List<Product> getProducts() {
        return productService.get();

    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

}