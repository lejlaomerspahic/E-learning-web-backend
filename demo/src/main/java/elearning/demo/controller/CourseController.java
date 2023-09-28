package elearning.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.dto.course.RatingRequest;
import elearning.demo.models.Course;
import elearning.demo.security.JwtService;
import elearning.demo.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public String createCourse(@RequestBody CourseCreatedRequest courseCreatedRequest) throws Exception {
        return courseService.create(courseCreatedRequest);
    }

    @GetMapping("/search/course/{key}")
    public List<Course> searchCourse(@PathVariable String key) {
        return courseService.search(key);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @PostMapping("/{courseId}/rating")
    public Double rating(@PathVariable Long courseId, @RequestBody RatingRequest ratingRequest,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        return courseService.rating(courseId, ratingRequest, userId);

    }

    @GetMapping("/{courseId}/checkRating")
    public ResponseEntity<Object> checkRating(@PathVariable Long courseId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        return courseService.check(courseId, userId);

    }
}
