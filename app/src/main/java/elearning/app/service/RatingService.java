package elearning.app.service;

import org.springframework.http.ResponseEntity;

import elearning.app.dto.rating.RatingCreatedRequest;
import elearning.app.model.Rating;

public interface RatingService {

    public Rating create(RatingCreatedRequest request);

    public ResponseEntity<?> getRatingCourse(Long param1, Long param2);
}