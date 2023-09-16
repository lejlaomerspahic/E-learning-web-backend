package elearning.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.models.User;
import elearning.demo.repository.UserRepository;
import elearning.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserCreatedRequest saveUser(UserCreatedRequest appUser) throws Exception {
        User usr = repository.findByEmail(appUser.getEmail());
        if (usr != null) {
            throw new Exception("User with email already exists.");
        }
        User user = new User(appUser.getFirstName(), appUser.getEmail(), appUser.getPassword(), appUser.getLocation());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        var returnUser = repository.save(user);
        return new UserCreatedRequest(returnUser.getFirstName(), returnUser.getEmail(), returnUser.getLocation());
    }

}
