package elearning.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
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
    @GeneratedValue()
    private Long id;
    private String username;
    private String email;
    private String password;
    private String location;

    private String picture;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorite;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + '}';
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_quiz", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private List<Quiz> quiz;

}
