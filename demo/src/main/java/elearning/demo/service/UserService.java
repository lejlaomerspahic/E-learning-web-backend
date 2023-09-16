package elearning.demo.service;

import org.springframework.stereotype.Service;

import elearning.demo.models.User;

@Service
public interface UserService {

    String addUser(User userInfo);

}
