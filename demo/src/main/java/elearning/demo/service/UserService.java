package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserCreatedResponse;
import elearning.demo.dto.user.UserLoginRequest;

@Service
public interface UserService {

    UserCreatedResponse saveUser(UserCreatedRequest appUser) throws Exception;

    Object createToken(UserLoginRequest user) throws Exception;

}
