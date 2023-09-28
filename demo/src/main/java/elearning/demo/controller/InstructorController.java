package elearning.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.service.InstructorService;

public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @PostMapping("/create")
    public String createCourse(@RequestBody InstructorCreatedRequest courseCreatedRequest) throws Exception {
        return instructorService.create(courseCreatedRequest);
    }

}
