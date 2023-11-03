package elearning.app.controller.ratingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.rating.RatingCreatedRequest;
import elearning.app.model.Rating;
import elearning.app.service.RatingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
@CrossOrigin(origins = "http://localhost:3000")

public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public Rating createRating(@RequestBody RatingCreatedRequest ratingCreatedRequest) throws Exception {

        System.out.println(ratingCreatedRequest);
        return ratingService.create(ratingCreatedRequest);
    }

}