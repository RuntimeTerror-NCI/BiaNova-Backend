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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import bianova.bianova.users.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class BiaNovaApplication {
    @Autowired
    private EmailService emailService;

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
    
//     @Bean
// 	public WebMvcConfigurer corsConfigurer() {
// 		return new WebMvcConfigurer() {
// 			@Override
// 			public void addCorsMappings(CorsRegistry registry) {
// 				registry.addMapping("*").allowedOrigins("http://localhost:3000");
// 			}
// 		};
// 	}
//}
