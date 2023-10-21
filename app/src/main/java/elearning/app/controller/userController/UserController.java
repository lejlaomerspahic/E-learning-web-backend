package elearning.app.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.user.JwtResponse;
import elearning.app.dto.user.RegisterReqDto;
import elearning.app.dto.user.UserCreatedResDto;
import elearning.app.dto.user.UserLoginReqDto;
import elearning.app.dto.user.UserUpdateReqDto;
import elearning.app.dto.user.UserUpdateResDto;
import elearning.app.model.User;
import elearning.app.service.UserService;
import elearning.app.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public JwtResponse authenticateAndGetToken(@RequestBody UserLoginReqDto authRequest) throws Exception {
        return userService.createToken(authRequest);
    }

    @PostMapping("/signup")
    public UserCreatedResDto generate(@RequestBody RegisterReqDto authRequest) throws Exception {
        return userService.saveUser(authRequest);
    }

    @GetMapping("/{id}")
    public User getSinge(@PathVariable Long id) throws Exception {

        User user = this.userService.getUser(id);
        if (user == null) {
            throw new Exception("User does not exist.");
        }

        return user;

    }

    @PutMapping("/update")
    public UserUpdateResDto updateUser(@RequestBody UserUpdateReqDto updatedUserData,
            @RequestHeader("Authorization") String authorizationHeader) throws Exception {
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.getUserIdFromToken(token);
        UserUpdateResDto updatedUser = userService.updateUser(userId, updatedUserData);
        return updatedUser;
    }

    // @PutMapping("/update/products")
    // public User updateUserProducts(@RequestBody UserUpadateProducts
    // updatedUserData,
    // @RequestHeader("Authorization") String authorizationHeader) throws
    // Exception {
    // String token = authorizationHeader.substring(7);
    // Long userId = jwtService.getUserIdFromToken(token);
    // User user = userService.updateUserProduct(userId, updatedUserData);
    // return user;
    // }
    //
    // @GetMapping("/status/{itemId}")
    // public String getStatus(@PathVariable String itemId,
    // @RequestHeader("Authorization") String authorizationHeader) {
    //
    // String token = authorizationHeader.substring(7);
    // Long userId = jwtService.getUserIdFromToken(token);
    // String status = userService.getStatus(userId, itemId);
    // return status;
    // }
    //
    // @GetMapping("/statusUpdate")
    // public void startScheduledTask() {
    // userService.updateStatus();
    // }

}
