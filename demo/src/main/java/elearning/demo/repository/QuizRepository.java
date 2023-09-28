package elearning.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(String category);
}