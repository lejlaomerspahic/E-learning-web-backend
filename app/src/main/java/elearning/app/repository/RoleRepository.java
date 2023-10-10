package elearning.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}