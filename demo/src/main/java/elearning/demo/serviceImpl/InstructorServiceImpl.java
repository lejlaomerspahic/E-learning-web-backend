package elearning.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.mapper.InstructorMapper;
import elearning.demo.repository.InstructorRepository;
import elearning.demo.service.InstructorService;

public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    InstructorMapper instructorMapper;

    @Override
    public String create(InstructorCreatedRequest courseCreatedRequest) {
        instructorRepository.save(instructorMapper.dtoToEntity(courseCreatedRequest));
        return "Course created successfully";
    }
}
