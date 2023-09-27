package elearning.demo.mapper;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.models.Course;

public interface CourseMapper {

    Course dtoToEntity(CourseCreatedRequest dto);

}
