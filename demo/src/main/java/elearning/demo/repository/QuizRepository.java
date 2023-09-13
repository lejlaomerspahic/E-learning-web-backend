package elearning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}