package elearning.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserLoginRequest;
import elearning.demo.models.User;
import elearning.demo.repository.UserRepository;
import elearning.demo.security.JwtService;
import elearning.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserCreatedRequest saveUser(UserCreatedRequest appUser) throws Exception {
        User usr = repository.findByEmail(appUser.getUsername());
        if (usr != null) {
            throw new Exception("User with this username already exists.");
        }
        User user = new User(appUser.getUsername(), appUser.getEmail(), appUser.getLocation());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setRole("USER_ROLE");
        var returnUser = repository.save(user);
        return new UserCreatedRequest(returnUser.getUsername(), returnUser.getEmail(), returnUser.getLocation());
    }

    @Override
    public String login(UserLoginRequest authRequest) throws Exception {
        org.springframework.security.core.Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = repository.findByUsername(authRequest.getUsername());
            Long userId = user.getId();
            return jwtService.generateToken(authRequest.getUsername(), userId);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
