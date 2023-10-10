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
import elearning.app.dto.user.UserUpdateResDto;
import elearning.app.model.Role;
import elearning.app.model.User;
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
    private final UserRepository _userRepository;
    private final RoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtUtil _jwtUtil;

    @Override
    public UserCreatedResDto saveUser(RegisterReqDto User) throws Exception {
        User usr = this._userRepository.findByEmail(User.getEmail());
        if (usr != null) {
            throw new Exception("User with email already exists.");
        }
        User user = new User(User.getUsername(), User.getEmail(), User.getPassword(), User.getLocation());
        user.setPassword(this._passwordEncoder.encode(User.getPassword()));
        var returnUser = this._userRepository.save(user);
        this.addRoleToUser(User.getEmail(), "USER");
        return new UserCreatedResDto(returnUser.getId(), returnUser.getUsername(), returnUser.getEmail(), returnUser.getLocation());
    }

    @Override
    public Role saveRole(Role saveRole) {
        Role role = this._roleRepository.findByName(saveRole.getName());
        if (role != null) {
            throw new IllegalStateException("Role already exists");
        }
        this._roleRepository.save(saveRole);
        return saveRole;
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = this._userRepository.findByEmail(email);
        Role role = this._roleRepository.findByName(roleName);
        if (user == null || role == null) {
            throw new IllegalStateException("User or role are not found.");
        }
        user.setRole(role);
    }

    @Override
    public User getUser(Long id) {
        User user = this._userRepository.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = this._userRepository.findAll();
        return users;
    }

    @Override
    public JwtResponse createToken(UserLoginReqDto user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        authenticate(username, password);
        User User = this._userRepository.findByEmail(username);
        if (user.getRememberMe().isPresent()) {
            if (user.getRememberMe().get() == false) { // ako user nije stavio
                                                       // rememberMe, nece se
                                                       // token ni kreirati
                return new JwtResponse(username, "", User.getRole().getName());

            } else {
                final UserDetails userDetails = loadUserByUsername(username);
                String newGeneratedToken = this._jwtUtil.generateToken(userDetails);
                return new JwtResponse(username, newGeneratedToken, User.getRole().getName());
            }
        }
        return new JwtResponse(username, "", User.getRole().getName());
    }

    private void authenticate(String username, String password) throws Exception {

        User authUser = this._userRepository.findByEmail(username);
        if (authUser == null) {
            throw new DisabledException("");
        }
        boolean passwordCorrect = this._passwordEncoder.matches(password, authUser.getPassword());
        if (!passwordCorrect) {
            throw new BadCredentialsException("Passwords do not match");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this._userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public UserUpdateResDto updateUser(Long userId, UserUpdateReqDto updatedUserData) throws Exception {
        User existingUser = _userRepository.findById(userId);

        if (existingUser == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        existingUser.setUsername(updatedUserData.getUsername());
        existingUser.setEmail(updatedUserData.getEmail());
        existingUser.setPassword(updatedUserData.getPassword());
        existingUser.setLocation(updatedUserData.getLocation());

        User updatedUser = _userRepository.save(existingUser);

        return new UserUpdateResDto(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getLocation());

    }
}
