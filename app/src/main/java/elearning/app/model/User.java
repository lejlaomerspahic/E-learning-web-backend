package elearning.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public User(String username, String email, String password, String location) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(Long id, String username, String email, String location, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.location = location;
        this.role = role;
    }

    @Id
    @GeneratedValue(

    )
    private Long id;
    @Column(name = "username")
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String location;
    private String picture;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;

}
