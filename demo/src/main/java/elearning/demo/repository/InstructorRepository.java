package elearning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}