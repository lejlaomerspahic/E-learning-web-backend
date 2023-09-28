package elearning.demo.service;

import java.util.List;
import java.util.Optional;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.dto.course.RatingRequest;
import elearning.demo.models.Course;

public interface CourseService {

    String create(CourseCreatedRequest courseCreatedRequest);

    List<Course> search(String key);

    Optional<Course> getCourse(Long id);

    Double rating(Long courseId, RatingRequest ratingRequest, Long userId);

}
