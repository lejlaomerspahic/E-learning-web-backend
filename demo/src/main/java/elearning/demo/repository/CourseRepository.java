package elearning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}