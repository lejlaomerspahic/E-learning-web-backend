package elearning.app.controller.instructorControler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.model.Instructor;
import elearning.app.service.InstructorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
@CrossOrigin(origins = "http://localhost:3000")

public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @GetMapping("/{id}")
    public Instructor getInstructor(@PathVariable Long id) {

        return instructorService.getInstructor(id);
    }

}