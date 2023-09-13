package elearning.demo.dto.user;

public class UserCreatedResponse {

    private Long id;
    private String FirstName;
    private String Email;

    public UserCreatedResponse(Long id, String firstName, String email) {
        super();
        this.id = id;
        FirstName = firstName;
        Email = email;
    }

}
