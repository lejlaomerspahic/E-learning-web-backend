package elearning.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.mapper.InstructorMapper;
import elearning.demo.models.Instructor;
import elearning.demo.repository.InstructorRepository;
import elearning.demo.service.InstructorService;

public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    InstructorMapper instructorMapper;

    @Override
    public String create(InstructorCreatedRequest instructorCreatedRequest) {
        instructorRepository.save(instructorMapper.dtoToEntity(instructorCreatedRequest));
        return "Instructor created successfully";
    }

    @Override
    public Instructor getInstructor(Long id) {
        return instructorRepository.findById(id).get();
    }
}
