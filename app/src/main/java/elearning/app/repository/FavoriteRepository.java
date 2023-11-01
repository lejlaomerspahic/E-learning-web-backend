package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import elearning.app.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId OR f.product.id = :productId OR f.course.id = :courseId")
    Favorite findFavoritesByUserOrProductOrCourse(Long userId, Long productId, Long courseId);
}
