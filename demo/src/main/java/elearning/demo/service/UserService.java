package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserLoginRequest;

@Service
public interface UserService {

    UserCreatedRequest saveUser(UserCreatedRequest appUser) throws Exception;

    String login(UserLoginRequest authRequest) throws Exception;

}
