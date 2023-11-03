package elearning.app.serviceImpl;

import java.util.Optional;

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

}