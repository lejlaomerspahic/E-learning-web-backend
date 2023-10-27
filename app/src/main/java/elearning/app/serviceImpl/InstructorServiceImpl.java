package elearning.app.serviceImpl;

import org.springframework.stereotype.Service;

import elearning.app.model.Instructor;
import elearning.app.repository.InstructorRepository;
import elearning.app.service.InstructorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;;

    @Transactional
    public Instructor getInstructor(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

}