package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.models.Instructor;

@Service
public interface InstructorService {

    String create(InstructorCreatedRequest instructorCreatedRequest);

    Instructor getInstructor(Long id);

}
