package elearning.app.controller.ratingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        return ratingService.create(ratingCreatedRequest);
    }

    @GetMapping("/courseRatings")
    public ResponseEntity<?> getCourseRatings(@RequestParam("param1") Long param1, @RequestParam("param2") Long param2) throws Exception {
        return ratingService.getRatingCourse(param1, param2);
    }
}