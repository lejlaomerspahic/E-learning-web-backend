package elearning.app.dto.user;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginReqDto {
    private String email;
    private String password;
    private Optional<Boolean> rememberMe;
}
