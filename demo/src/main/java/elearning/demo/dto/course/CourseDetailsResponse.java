package elearning.demo.dto.course;

import elearning.demo.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDetailsResponse {

    private Course course;
    private Long userRating;
    private int allRatings;
    private double averageRating;
}
