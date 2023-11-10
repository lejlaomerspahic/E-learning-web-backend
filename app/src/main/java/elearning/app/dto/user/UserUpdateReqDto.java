package elearning.app.dto.user;

import lombok.Data;

@Data
public class UserUpdateReqDto {
    private String username;
    private String email;
    private String password;
    private String location;
    private String url;
}
