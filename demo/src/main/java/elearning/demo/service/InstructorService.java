package elearning.demo.service;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.models.Instructor;

public interface InstructorService {

    String create(InstructorCreatedRequest instructorCreatedRequest);

    Instructor getInstructor(Long id);

}
