package elearning.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import elearning.app.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT c FROM Quiz c WHERE " + "c.category LIKE %:category%")
    List<Quiz> findByCategory(@Param("category") String category);

    @Query("SELECT c FROM Quiz c WHERE " + "c.name LIKE %:searchString% OR " + "c.difficulty LIKE %:searchString% OR "
            + "c.category LIKE %:searchString% OR " + "c.duration LIKE %:searchString% OR " + "c.description LIKE %:searchString%")
    List<Quiz> findQuizzesBySearchString(@Param("searchString") String searchString);

}
