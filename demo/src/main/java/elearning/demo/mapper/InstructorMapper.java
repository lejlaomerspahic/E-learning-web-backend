package elearning.demo.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.models.Instructor;

@Mapper
public interface InstructorMapper {

    @InheritConfiguration(name = "dtoToEntity")
    Instructor dtoToEntity(InstructorCreatedRequest dto);
}
