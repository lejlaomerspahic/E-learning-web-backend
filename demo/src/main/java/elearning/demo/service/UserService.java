package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;

@Service
public interface UserService {

    UserCreatedRequest saveUser(UserCreatedRequest appUser) throws Exception;

}
