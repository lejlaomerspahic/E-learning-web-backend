package elearning.app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedResDto {
    private Long id;
    private String username;
    private String email;
    private String location;
}
