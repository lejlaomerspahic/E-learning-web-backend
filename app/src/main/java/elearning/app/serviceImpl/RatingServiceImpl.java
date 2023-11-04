package elearning.app.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import elearning.app.dto.rating.RatingCreatedRequest;
import elearning.app.model.Course;
import elearning.app.model.Product;
import elearning.app.model.Rating;
import elearning.app.model.User;
import elearning.app.repository.CourseRepository;
import elearning.app.repository.ProductRepository;
import elearning.app.repository.RatingRepository;
import elearning.app.repository.UserRepository;
import elearning.app.service.RatingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final ProductRepository productRepository;

    @Override
    public Rating create(RatingCreatedRequest request) {
        Optional<Rating> existingRating = ratingRepository.findRatingByUserAndCourse(request.getUser(), request.getCourse());

        if (existingRating.isPresent()) {
            Rating rating = existingRating.get();
            rating.setRating(request.getRating());
            return ratingRepository.save(rating);
        } else {
            Rating newRating = new Rating();
            if (request.getCourse() != null) {
                Course course = courseRepository.getById(request.getCourse());
                newRating.setCourse(course);
            }
            if (request.getUser() != null) {
                User user = userRepository.findById(request.getUser());
                newRating.setUser(user);
            }
            if (request.getProduct() != null) {
                Product product = productRepository.getById(request.getProduct());
                newRating.setProduct(product);
            }
            newRating.setRating(request.getRating());
            return ratingRepository.save(newRating);
        }
    }

    @Override
    public ResponseEntity<?> getRatingCourse(Long param1, Long param2) {
        try {

            Course course = courseRepository.findByIdAndRatingsUserId(param1, param2);
            if (course == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
            }

            List<Rating> ratings = course.getRatings();
            Rating userRating = null;
            for (Rating rating : ratings) {
                if (rating.getUser().getId() == param2) {
                    userRating = rating;
                    break;
                }
            }

            int totalRatings = ratings.size();
            int totalRatingSum = 0;
            for (Rating rating : ratings) {
                totalRatingSum += rating.getRating();
            }
            double averageRating = totalRatings > 0 ? (double) totalRatingSum / totalRatings : 0;

            Map<String, Object> response = new HashMap<>();
            response.put("course", course);
            response.put("userRating", userRating);
            response.put("averageRating", averageRating);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get course details");
        }
    }

}