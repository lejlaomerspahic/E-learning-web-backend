package elearning.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import elearning.demo.models.User;
import elearning.demo.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username));
        return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findById(userId);
        return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
    }

}