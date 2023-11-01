package elearning.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import elearning.app.dto.product.ProductCreateRequest;
import elearning.app.model.Product;
import elearning.app.repository.ProductRepository;
import elearning.app.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setTitle(productCreateRequest.getTitle());
        product.setDescription(productCreateRequest.getDescription());
        product.setImageUrl(productCreateRequest.getImageUrl());
        product.setPrice(productCreateRequest.getPrice());
        product.setProductLocation(productCreateRequest.getProductLocation());
        product.setSupplier(productCreateRequest.getSupplier());
        return productRepository.save(product);

    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> get() {
        return productRepository.findAll();
    }

}