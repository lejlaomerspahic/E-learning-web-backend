package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserLoginRequest;
import elearning.demo.dto.user.UserUpadateProducts;
import elearning.demo.dto.user.UserUpdateRequest;
import elearning.demo.models.User;

@Service
public interface UserService {

    UserCreatedRequest saveUser(UserCreatedRequest appUser) throws Exception;

    String login(UserLoginRequest authRequest) throws Exception;

    UserUpdateRequest updateUser(Long userId, UserUpdateRequest updatedUserData) throws Exception;

    UserCreatedRequest getUser(Long userId) throws Exception;

    User updateUserProduct(Long userId, UserUpadateProducts updatedUserData) throws Exception;

    String getStatus(Long userId, String itemId);

    void updateStatus();
}
