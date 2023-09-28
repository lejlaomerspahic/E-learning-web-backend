package elearning.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.instructor.InstructorCreatedRequest;
import elearning.demo.models.Instructor;
import elearning.demo.service.InstructorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
@CrossOrigin("*")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @PostMapping("/create")
    public String createinstructor(@RequestBody InstructorCreatedRequest instructorCreatedRequest) throws Exception {
        return instructorService.create(instructorCreatedRequest);
    }

    @GetMapping("/{id}")
    public Instructor getInstructor(@PathVariable Long id) {
        return instructorService.getInstructor(id);
    }

}
