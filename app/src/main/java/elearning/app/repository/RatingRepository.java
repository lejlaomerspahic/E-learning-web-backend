package elearning.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import elearning.app.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT r FROM Rating r WHERE (r.user.id = :userId AND r.course.id = :courseId) OR (r.user.id = :userId AND r.product.id = :productId)")
    Optional<Rating> findRatingByUserAndCourseOrProduct(@Param("userId") Long userId, @Param("courseId") Long courseId,
            @Param("productId") Long productId);

}
