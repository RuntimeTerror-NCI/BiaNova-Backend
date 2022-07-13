package bianova.bianova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import bianova.bianova.users.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class BiaNovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiaNovaApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            System.out.println("blah");
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            userService.createRole(userRole);
            userService.createRole(adminRole);
            userService.deleteUserByUsername("mike1");
            userService.createUser(new User("mike1", "abc123", "abc@123", List.of(userRole)));
            userService.deleteUserByUsername("mike2");
            userService.createUser(new User("mike2", "abc123", "abc@123", List.of(userRole, adminRole)));
        };
    }
}
