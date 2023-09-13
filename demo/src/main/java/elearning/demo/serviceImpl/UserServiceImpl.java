package elearning.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import elearning.demo.dto.user.JwtResponse;
import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserCreatedResponse;
import elearning.demo.dto.user.UserLoginRequest;
import elearning.demo.models.Role;
import elearning.demo.models.User;
import elearning.demo.repository.RoleRepository;
import elearning.demo.repository.UserRepository;
import elearning.demo.security.JwtTokenProvider;
import elearning.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository _userRepository;
    private final RoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtTokenProvider _jwtUtil;

    @Override
    public UserCreatedResponse saveUser(UserCreatedRequest appUser) throws Exception {

        User usr = this._userRepository.findByEmail(appUser.getEmail());
        if (usr != null) {
            throw new Exception("User with email already exists.");
        }
        User user = new User(appUser.getFirstName(), appUser.getEmail(), appUser.getPassword(), appUser.getLocation());
        user.setPassword(this._passwordEncoder.encode(appUser.getPassword()));
        var returnUser = this._userRepository.save(user);
        this.addRoleToUser(appUser.getEmail(), "USER");
        return new UserCreatedResponse(returnUser.getId(), returnUser.getFirstName(), returnUser.getEmail());
    }

    public void addRoleToUser(String email, String roleName) {
        User user = this._userRepository.findByEmail(email);
        Role role = this._roleRepository.findByName(roleName);
        if (user == null || role == null) {
            throw new IllegalStateException("User or role are not found.");
        }
        user.setRole(role);
    }

    @Override
    public JwtResponse createToken(UserLoginRequest user) throws Exception {
        String username = user.getEmail();
        String password = user.getPassword();
        authenticate(username, password);
        User appUser = this._userRepository.findByEmail(username);

        final UserDetails userDetails = loadUserByUsername(username);
        String newGeneratedToken = this._jwtUtil.generateToken(userDetails);
        return new JwtResponse(username, newGeneratedToken, appUser.getRole().getName());
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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this._userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
