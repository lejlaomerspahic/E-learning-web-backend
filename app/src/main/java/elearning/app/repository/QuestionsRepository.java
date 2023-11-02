package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elearning.app.model.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
