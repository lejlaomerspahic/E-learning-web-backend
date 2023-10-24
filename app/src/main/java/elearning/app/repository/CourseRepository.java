package elearning.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import elearning.app.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE " + "c.category LIKE %:category%")
    List<Course> findByCategory(@Param("category") String category);

    @Query("SELECT c FROM Course c WHERE " + "c.name LIKE %:searchString% OR " + "c.duration LIKE %:searchString% OR "
            + "c.category LIKE %:searchString% OR " + "c.level LIKE %:searchString% OR " + "c.language LIKE %:searchString%")
    List<Course> findCoursesBySearchString(@Param("searchString") String searchString);

}
