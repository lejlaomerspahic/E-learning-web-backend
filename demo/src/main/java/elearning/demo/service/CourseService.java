package elearning.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.dto.course.RatingRequest;
import elearning.demo.models.Course;

@Service
public interface CourseService {

    String create(CourseCreatedRequest courseCreatedRequest);

    List<Course> search(String key);

    Course getCourse(Long id);

    Double rating(Long courseId, RatingRequest ratingRequest, Long userId);

    ResponseEntity<Object> check(Long courseId, Long userId);

}
