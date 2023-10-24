package elearning.app.controller.courseControler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.course.CourseCreatedRequest;
import elearning.app.model.Course;
import elearning.app.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")

public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public Course createCourse(@RequestBody CourseCreatedRequest courseCreatedRequest) throws Exception {
        return courseService.create(courseCreatedRequest);
    }

    @GetMapping("/search/{key}")
    public List<Course> searchCourse(@PathVariable String key) {
        return courseService.search(key);

    }

    @GetMapping("/search/info/{key}")
    public List<Course> searchCourseInput(@PathVariable String key) {
        return courseService.searchCourse(key);

    }

    @GetMapping("/{id}")
    public Optional<Course> getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    // @PostMapping("/{courseId}/rating")
    // public Double rating(@PathVariable Long courseId, @RequestBody
    // RatingRequest ratingRequest,
    // @RequestHeader("Authorization") String authorizationHeader) {
    // String token = authorizationHeader.substring(7);
    // Long userId = jwtService.getUserIdFromToken(token);
    // return courseService.rating(courseId, ratingRequest, userId);
    //
    // }
}