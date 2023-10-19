package elearning.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elearning.app.dto.course.CourseCreatedRequest;
import elearning.app.mapper.CourseMapper;
import elearning.app.model.Course;
import elearning.app.repository.CourseRepository;
import elearning.app.repository.UserRepository;
import elearning.app.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Course create(CourseCreatedRequest courseCreatedRequest) {
        System.out.println("CourseCreatedRequest");
        System.out.println(courseCreatedRequest);

        Course course = new Course();
        course.setName(courseCreatedRequest.getName());
        course.setDescription(courseCreatedRequest.getDescription());
        course.setCategory(courseCreatedRequest.getCategory());
        course.setIcon(courseCreatedRequest.getIcon());
        course.setDuration(courseCreatedRequest.getDuration());
        course.setImageUrl(courseCreatedRequest.getImageUrl());
        course.setIcon(courseCreatedRequest.getIcon());
        course.setInfo(courseCreatedRequest.getInfo());
        course.setLevel(courseCreatedRequest.getLevel());
        course.setLanguage(courseCreatedRequest.getLanguage());
        course.setLastUpdated(courseCreatedRequest.getLastUpdated());
        course.setVideoId(courseCreatedRequest.getVideoId());
        return courseRepository.save(course);

    }

    @Override
    public List<Course> search(String key) {
        return courseRepository.findByCategory(key);
    }

    @Override
    public Optional<Course> getCourse(Long id) {
        return courseRepository.findById(id);
    }
    //
    // @Override
    // public Double rating(Long courseId, RatingRequest ratingRequest, Long
    // userId) {
    // Optional<Course> optionalCourse = courseRepository.findById(courseId);
    // Long rating = ratingRequest.getRating();
    // if (rating < 1 || rating > 5) {
    // return null;
    // }
    // Course course = optionalCourse.get();
    // Rating userRating = course.getRatings().stream().filter(r ->
    // r.getUser().equals(userId)).findFirst().get();
    // if (userRating != null) {
    // userRating.setRating(rating);
    // } else {
    // Optional<User> user = userRepository.findById(userId);
    // Rating userRatingg = new Rating(user.get(), rating);
    // course.getRatings().add(userRating);
    // }
    // courseRepository.save(course);
    // Double averageRating = this.calculateAverageRating(course);
    // return averageRating;
    // }
    //
    // public double calculateAverageRating(Course course) {
    // List<Rating> ratings = course.getRatings();
    // if (ratings.isEmpty()) {
    // return 0.0;
    // }
    // Long totalRatingSum =
    // ratings.stream().mapToLong(Rating::getRating).sum();
    // int totalRatings = ratings.size();
    // return (double) totalRatingSum / totalRatings;
    // }
    //
    // @Override
    // public ResponseEntity<Object> check(Long courseId, Long userId) {
    // try {
    // Optional<Course> course = courseRepository.findById(courseId);
    // if (course == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not
    // found");
    // }
    //
    // Long userRating = courseRepository.findUserRatingForCourse(userId,
    // courseId);
    //
    // List<Rating> allRatings = course.get().getRatings();
    //
    // double averageRating = this.calculateAverageRating(course.get());
    //
    // return ResponseEntity
    // .ok(new elearning.demo.dto.course.CourseDetailsResponse(course.get(),
    // userRating, allRatings.size(), averageRating));
    // } catch (Exception e) {
    // e.printStackTrace();
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to
    // get course details");
    // }
    // }

}