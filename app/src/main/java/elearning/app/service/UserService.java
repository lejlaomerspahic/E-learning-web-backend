package elearning.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import elearning.app.dto.user.JwtResponse;
import elearning.app.dto.user.RegisterReqDto;
import elearning.app.dto.user.ResultRequest;
import elearning.app.dto.user.UserCreatedResDto;
import elearning.app.dto.user.UserLoginReqDto;
import elearning.app.dto.user.UserUpdateReqDto;
import elearning.app.model.QuizResult;
import elearning.app.model.Role;
import elearning.app.model.User;

public interface UserService {

    UserCreatedResDto saveUser(RegisterReqDto appUser) throws Exception;

    Role saveRole(Role saveRole);

    void addRoleToUser(String email, String roleName);

    User getUser(Long id);

    List<User> getUsers();

    JwtResponse createToken(UserLoginReqDto user) throws Exception;

    JwtResponse updateUser(Long userId, UserUpdateReqDto updatedUserData) throws Exception;

    QuizResult createUserQuiz(Long userId, ResultRequest resultRequest);

    List<QuizResult> getCourseResult(Long userId);

    ResponseEntity<List<Map<String, Object>>> getQuizzesByUserId(Long userId);

}