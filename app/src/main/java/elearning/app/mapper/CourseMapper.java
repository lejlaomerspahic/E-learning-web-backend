package elearning.app.mapper;

import org.mapstruct.Mapper;

import elearning.app.dto.course.CourseCreatedRequest;
import elearning.app.model.Course;

@Mapper
public interface CourseMapper {
    Course dtoToEntity(CourseCreatedRequest dto);
}
