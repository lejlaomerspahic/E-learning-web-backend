package elearning.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import elearning.app.model.Role;
import elearning.app.serviceImpl.UserServiceImpl;

@SpringBootApplication
@ComponentScan({ "elearning.app.service", "elearning.app.serviceImpl" })
@MapperScan("elearning.app.mapper")
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserServiceImpl userService) {
        return args -> {
            userService.saveRole(new Role(null, "USER"));
            userService.saveRole(new Role(null, "ADMIN"));

        };
    }
}
