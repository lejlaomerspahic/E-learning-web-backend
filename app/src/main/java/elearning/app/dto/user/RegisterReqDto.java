package elearning.app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterReqDto {
    private String username;
    private String email;
    private String password;
    private String location;
}
