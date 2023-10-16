package elearning.app.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import elearning.app.dto.course.CourseCreatedRequest;
import elearning.app.model.Course;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {
    Course dtoToEntity(CourseCreatedRequest dto);
}
