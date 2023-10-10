package elearning.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import elearning.app.dto.user.RegisterReqDto;
import elearning.app.model.Role;
import elearning.app.serviceImpl.UserServiceImpl;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserServiceImpl userService) {
        return args -> {
            userService.saveRole(new Role(null, "USER"));
            userService.saveRole(new Role(null, "ADMIN"));
            userService.saveUser(new RegisterReqDto("mahir", "mahirprcanovic@gmail.com", "1234", "Zenica"));
            userService.saveUser(new RegisterReqDto("asim", "asimb@gmail.com", "1234", "Zenica"));
            userService.addRoleToUser("mahirprcanovic@gmail.com", "ADMIN");
            userService.addRoleToUser("asimb@gmail.com", "USER");
        };
    }
}
