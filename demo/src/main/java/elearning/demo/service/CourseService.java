package elearning.demo.service;

import elearning.demo.dto.course.CourseCreatedRequest;

public interface CourseService {

    String create(CourseCreatedRequest courseCreatedRequest);

}
