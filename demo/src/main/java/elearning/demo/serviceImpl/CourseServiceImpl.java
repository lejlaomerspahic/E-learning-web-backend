package elearning.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import elearning.demo.dto.course.CourseCreatedRequest;
import elearning.demo.mapper.CourseMapper;
import elearning.demo.repository.CourseRepository;
import elearning.demo.service.CourseService;

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public String create(CourseCreatedRequest courseCreatedRequest) {
        courseRepository.save(courseMapper.dtoToEntity(courseCreatedRequest));
        return "Course created successfully";
    }

}
