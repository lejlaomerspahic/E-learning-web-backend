package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import elearning.app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT c FROM Product c " + "JOIN c.ratings r " + "WHERE c.id = :productId AND r.user.id = :userId")
    Product findByIdAndRatingsUserId(Long productId, Long userId);

}
