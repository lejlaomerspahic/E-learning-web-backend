package elearning.app.service;

import elearning.app.dto.rating.RatingCreatedRequest;
import elearning.app.model.Rating;

public interface RatingService {
    ;
    public Rating create(RatingCreatedRequest request);

}