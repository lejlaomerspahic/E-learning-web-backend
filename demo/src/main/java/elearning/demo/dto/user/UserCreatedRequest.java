package elearning.demo.dto.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreatedRequest {

    private String FirstName;
    private String Email;
    private String Location;
    private String Password;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserCreatedRequest(String firstName, String email, String location) {
        super();
        FirstName = firstName;
        Email = email;
        Location = location;
    }

}
