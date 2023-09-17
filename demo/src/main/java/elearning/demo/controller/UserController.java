package elearning.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserLoginRequest;
import elearning.demo.dto.user.UserUpdateRequest;
import elearning.demo.security.JwtService;
import elearning.demo.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
@Api(value = "User Authentication", tags = "User Authentication")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody UserLoginRequest authRequest) throws Exception {
        return userService.login(authRequest);
    }

    @PostMapping("/generate")
    public UserCreatedRequest generate(@RequestBody UserCreatedRequest authRequest) throws Exception {
        return userService.saveUser(authRequest);
    }

    @PutMapping("/update")
    public UserUpdateRequest updateUser(@RequestBody UserUpdateRequest updatedUserData,
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        Long userId = jwtService.getUserIdFromToken(token);

        UserUpdateRequest updatedUser = userService.updateUser(userId, updatedUserData);
        jwtService.generateToken(updatedUserData.getUsername(), userId);
        return updatedUser;
    }
}
