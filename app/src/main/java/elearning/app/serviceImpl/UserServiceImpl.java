package elearning.app.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import elearning.app.dto.user.JwtResponse;
import elearning.app.dto.user.RegisterReqDto;
import elearning.app.dto.user.UserCreatedResDto;
import elearning.app.dto.user.UserLoginReqDto;
import elearning.app.dto.user.UserUpdateReqDto;
import elearning.app.model.Quiz;
import elearning.app.model.Role;
import elearning.app.model.User;
import elearning.app.repository.QuizRepository;
import elearning.app.repository.RoleRepository;
import elearning.app.repository.UserRepository;
import elearning.app.service.UserService;
import elearning.app.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final QuizRepository quizRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserCreatedResDto saveUser(RegisterReqDto User) throws Exception {
        User usr = this.userRepository.findByEmail(User.getEmail());
        if (usr != null) {
            throw new Exception("User with email already exists.");
        }
        User user = new User(User.getUsername(), User.getEmail(), User.getPassword(), User.getLocation());
        user.setPassword(this.passwordEncoder.encode(User.getPassword()));
        var returnUser = this.userRepository.save(user);
        this.addRoleToUser(User.getEmail(), "USER");
        return new UserCreatedResDto(returnUser.getId(), returnUser.getUsername(), returnUser.getEmail(), returnUser.getLocation());
    }

    @Override
    public Role saveRole(Role saveRole) {
        Role existingRole = this.roleRepository.findByName(saveRole.getName());
        if (existingRole != null) {
            existingRole.setName(saveRole.getName());
            return this.roleRepository.save(existingRole);
        } else {
            this.roleRepository.save(saveRole);
            return saveRole;
        }
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = this.userRepository.findByEmail(email);
        Role role = this.roleRepository.findByName(roleName);
        if (user == null || role == null) {
            throw new IllegalStateException("User or role are not found.");
        }
        user.setRole(role);
    }

    @Override
    public User getUser(Long id) {
        User user = this.userRepository.findById(id);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public JwtResponse createToken(UserLoginReqDto user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();

        authenticate(email, password);
        User User = this.userRepository.findByEmail(email);
        JwtResponse response;

        if (user.getRememberMe().isPresent()) {
            if (user.getRememberMe().get()) {
                final UserDetails userDetails = loadUserByUsername(email);
                String newGeneratedToken = this.jwtUtil.generateToken(userDetails);
                response = new JwtResponse(User, newGeneratedToken);
            } else {
                response = new JwtResponse(User, "");
            }
        } else {
            response = new JwtResponse(User, "");
        }

        return response;
    }

    private void authenticate(String username, String password) throws Exception {
        User authUser = this.userRepository.findByEmail(username);
        if (authUser == null) {
            throw new DisabledException("");
        }
        boolean passwordCorrect = this.passwordEncoder.matches(password, authUser.getPassword());
        if (!passwordCorrect) {
            throw new BadCredentialsException("Passwords do not match");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public JwtResponse updateUser(Long userId, UserUpdateReqDto updatedUserData) throws Exception {
        User existingUser = userRepository.findById(userId);

        if (existingUser == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        if (updatedUserData.getUsername() != null) {
            existingUser.setUsername(updatedUserData.getUsername());
        }
        if (updatedUserData.getEmail() != null) {
            existingUser.setEmail(updatedUserData.getEmail());
        }
        if (updatedUserData.getPassword() != null) {
            existingUser.setPassword(updatedUserData.getPassword());
        }
        if (updatedUserData.getLocation() != null) {
            existingUser.setLocation(updatedUserData.getLocation());
        }
        if (updatedUserData.getUrl() != null) {
            existingUser.setPicture(updatedUserData.getUrl());
        }

        User updatedUser = userRepository.save(existingUser);

        final UserDetails userDetails = loadUserByUsername(updatedUser.getEmail());
        String newGeneratedToken = this.jwtUtil.generateToken(userDetails);
        JwtResponse response = new JwtResponse(updatedUser, newGeneratedToken);

        return response;

    }

    @Override
    public User createUserQuiz(Long userId, Long quizId) {
        User existingUser = userRepository.findById(userId);

        List<Quiz> existingQuizzes = existingUser.getQuiz();
        Quiz quiz = quizRepository.getById(quizId);

        existingQuizzes.add(quiz);

        existingUser.setQuiz(existingQuizzes);
        userRepository.save(existingUser);

        return existingUser;
    }
}
