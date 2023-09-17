package elearning.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;
import elearning.demo.repository.ProductRepository;
import elearning.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductCreatedReqRes createProduct(ProductCreatedReqRes productCreatedReqRes) {
        Product product = new Product();
        product.setTitle(productCreatedReqRes.getTitle());
        product.setSupplier(productCreatedReqRes.getSupplier());
        product.setPrice(productCreatedReqRes.getPrice());
        product.setImageUrl(productCreatedReqRes.getImageUrl());
        product.setDescription(productCreatedReqRes.getDescription());
        product.setProductLocation(productCreatedReqRes.getProductLocation());

        Product savedProduct = productRepository.save(product);

        return new ProductCreatedReqRes(savedProduct.getTitle(), savedProduct.getSupplier(), savedProduct.getPrice().toString(),
                savedProduct.getImageUrl(), savedProduct.getDescription(), savedProduct.getProductLocation());

    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findById((long) id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }
}
