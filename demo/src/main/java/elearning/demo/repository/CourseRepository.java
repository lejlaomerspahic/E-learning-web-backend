package elearning.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import elearning.demo.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCategory(String category);

    @Query("SELECT r.rating FROM Rating r WHERE r.user.id = :userId AND r.course.id = :courseId")
    Long findUserRatingForCourse(@Param("userId") Long userId, @Param("courseId") Long courseId);

}