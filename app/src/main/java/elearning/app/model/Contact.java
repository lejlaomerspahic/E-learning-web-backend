package elearning.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue()
    private Long id;
    private String email;
    private String phone;
    private String website;

}
