package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elearning.app.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
