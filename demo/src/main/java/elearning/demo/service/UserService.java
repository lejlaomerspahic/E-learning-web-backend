package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;

@Service
public interface UserService {
    //
    // UserCreatedResponse signUp(UserCreatedRequest appUser) throws Exception;
    //
    // Object login(UserLoginRequest user) throws Exception;

    UserCreatedRequest updateUser(UserCreatedRequest user);

}
