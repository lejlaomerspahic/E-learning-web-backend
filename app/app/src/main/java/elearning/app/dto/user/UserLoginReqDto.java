package elearning.app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class UserLoginReqDto {
    private String username;
    private String password;
    private Optional<Boolean> rememberMe ;
}
