package elearning.app.service;

import java.util.List;
import java.util.Optional;

import elearning.app.dto.course.CourseCreatedRequest;
import elearning.app.model.Course;

public interface CourseService {

    Course create(CourseCreatedRequest courseCreatedRequest);

    List<Course> search(String key);

    Optional<Course> getCourse(Long id);

    List<Course> searchCourse(String key);

    List<Course> get();

    // Double rating(Long courseId, RatingRequest ratingRequest, Long userId);

}