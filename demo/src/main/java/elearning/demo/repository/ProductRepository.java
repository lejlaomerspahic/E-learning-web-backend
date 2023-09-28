package elearning.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import elearning.demo.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE " + "LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%')) OR "
            + "LOWER(p.supplier) LIKE LOWER(CONCAT('%', :key, '%')) OR " + "LOWER(p.description) LIKE LOWER(CONCAT('%', :key, '%')) OR "
            + "LOWER(p.imageUrl) LIKE LOWER(CONCAT('%', :key, '%')) OR " + "LOWER(p.productLocation) LIKE LOWER(CONCAT('%', :key, '%'))")
    List<Product> searchProducts(@Param("key") String key);

    @Query("SELECT r.rating FROM Rating r WHERE r.user.id = :userId AND r.quiz.id = :quizId")
    Long findUserRatingForProduct(@Param("userId") Long userId, @Param("quizId") Long quizId);

}
