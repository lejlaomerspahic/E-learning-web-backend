package elearning.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.dto.course.RatingRequest;
import elearning.demo.mapper.CourseMapper;
import elearning.demo.models.Course;
import elearning.demo.models.Rating;
import elearning.demo.models.User;
import elearning.demo.repository.CourseRepository;
import elearning.demo.repository.UserRepository;
import elearning.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(CourseCreatedRequest courseCreatedRequest) {
        courseRepository.save(courseMapper.dtoToEntity(courseCreatedRequest));
        return "Course created successfully";
    }

    @Override
    public List<Course> search(String key) {

        return courseRepository.findByCategory(key);

    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Double rating(Long courseId, RatingRequest ratingRequest, Long userId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        Long rating = ratingRequest.getRating();
        if (rating < 1 || rating > 5) {
            return null;
        }

        Course course = optionalCourse.get();

        Rating userRating = course.getRatings().stream().filter(r -> r.getUser().equals(userId)).findFirst().get();

        if (userRating != null) {
            userRating.setRating(rating);
        } else {
            Optional<User> user = userRepository.findById(userId);
            Rating userRatingg = new Rating(user.get(), rating);
            course.getRatings().add(userRating);
        }

        courseRepository.save(course);

        Double averageRating = this.calculateAverageRating(course);

        return averageRating;

    }

    public double calculateAverageRating(Course course) {
        List<Rating> ratings = course.getRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        Long totalRatingSum = ratings.stream().mapToLong(Rating::getRating).sum();
        int totalRatings = ratings.size();

        return (double) totalRatingSum / totalRatings;
    }

    @Override
    public ResponseEntity<Object> check(Long courseId, Long userId) {
        try {
            Optional<Course> course = courseRepository.findById(courseId);
            if (course == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
            }

            Long userRating = courseRepository.findUserRatingForCourse(userId, courseId);

            List<Rating> allRatings = course.get().getRatings();

            double averageRating = this.calculateAverageRating(course.get());

            return ResponseEntity
                    .ok(new elearning.demo.dto.course.CourseDetailsResponse(course.get(), userRating, allRatings.size(), averageRating));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get course details");
        }
    }

}
