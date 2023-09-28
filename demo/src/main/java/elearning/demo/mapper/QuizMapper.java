package elearning.demo.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import elearning.demo.dto.quiz.QuizCreatedRequest;
import elearning.demo.models.Quiz;

@Mapper
public interface QuizMapper {

    @InheritConfiguration(name = "dtoToEntity")
    Quiz dtoToEntity(QuizCreatedRequest dto);

}
