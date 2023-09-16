package elearning.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import elearning.demo.models.Product;
import elearning.demo.repository.ProductRepository;
import elearning.demo.service.ProductService;

public class ProductServiceImpl implements ProductService {

    List<Product> productList = null;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product getProduct(int id) {
        return productList.stream().filter(product -> product.getId() == id).findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }

}
