package elearning.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;

    private String email;
    private String location;
    private String username;
    private String password;

}
