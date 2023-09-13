package elearning.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.demo.dto.user.UserLoginRequest;
import elearning.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService _userService;

    @PostMapping("/login")
    public ResponseEntity<?> createJwtToken(@RequestBody UserLoginRequest user) throws Exception {
        try {
            return ResponseEntity.ok().body(this._userService.createToken(user));
        } catch (DisabledException ex) {
            return ResponseEntity.badRequest().body("User with your email does not exist");
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body("Password is not correct.");
        }

    }
}
