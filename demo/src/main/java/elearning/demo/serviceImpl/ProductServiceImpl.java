package elearning.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import elearning.demo.dto.course.RatingRequest;
import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.mapper.ProductMapper;
import elearning.demo.models.Product;
import elearning.demo.models.Rating;
import elearning.demo.models.User;
import elearning.demo.repository.ProductRepository;
import elearning.demo.repository.UserRepository;
import elearning.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createProduct(ProductCreatedReqRes productCreatedReqRes) {
        // Product product = new Product();
        // product.setTitle(productCreatedReqRes.getTitle());
        // product.setSupplier(productCreatedReqRes.getSupplier());
        // product.setPrice(productCreatedReqRes.getPrice());
        // product.setImageUrl(productCreatedReqRes.getImageUrl());
        // product.setDescription(productCreatedReqRes.getDescription());
        // product.setProductLocation(productCreatedReqRes.getProductLocation());
        //
        // Product savedProduct = productRepository.save(product);
        //
        // return new ProductCreatedReqRes(savedProduct.getTitle(),
        // savedProduct.getSupplier(), savedProduct.getPrice().toString(),
        // savedProduct.getImageUrl(), savedProduct.getDescription(),
        // savedProduct.getProductLocation());
        productRepository.save(productMapper.dtoToEntity(productCreatedReqRes));
        return "Product created successfully";
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> search(String key) {
        List<Product> result = productRepository.searchProducts(key);
        return result;
    }

    @Override
    public Double rating(Long productId, RatingRequest ratingRequest, Long userId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        Long rating = ratingRequest.getRating();
        if (rating < 1 || rating > 5) {
            return null;
        }

        Product product = optionalProduct.get();

        Rating userRating = product.getRatings().stream().filter(r -> r.getUser().equals(userId)).findFirst().get();

        if (userRating != null) {
            userRating.setRating(rating);
        } else {
            Optional<User> user = userRepository.findById(userId);
            Rating userRatingg = new Rating(user.get(), rating);
            product.getRatings().add(userRating);
        }

        productRepository.save(product);

        Double averageRating = this.calculateAverageRating(product);

        return averageRating;

    }

    public double calculateAverageRating(Product product) {
        List<Rating> ratings = product.getRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        Long totalRatingSum = ratings.stream().mapToLong(Rating::getRating).sum();
        int totalRatings = ratings.size();

        return (double) totalRatingSum / totalRatings;
    }

    @Override
    public ResponseEntity<Object> check(Long productId, Long userId) {
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
            }

            Long userRating = productRepository.findUserRatingForProduct(userId, productId);

            List<Rating> allRatings = product.get().getRatings();

            double averageRating = this.calculateAverageRating(product.get());

            return ResponseEntity
                    .ok(new elearning.demo.dto.product.ProductDetailsResponse(product.get(), userRating, allRatings.size(), averageRating));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get course details");
        }
    }
}
