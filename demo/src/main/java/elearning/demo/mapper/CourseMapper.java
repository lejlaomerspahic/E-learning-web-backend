package elearning.demo.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.models.Course;

@Mapper
public interface CourseMapper {

    @InheritConfiguration(name = "dtoToEntity")
    Course dtoToEntity(CourseCreatedRequest dto);

}
