package elearning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByFirstName(String firstName);

}
