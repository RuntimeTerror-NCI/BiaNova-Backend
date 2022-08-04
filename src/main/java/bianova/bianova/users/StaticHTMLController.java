package bianova.bianova.users;

// reference: https://www.baeldung.com/spring-mvc-return-html
// and https://initialcommit.com/blog/serve-static-resources-with-spring-boot
// accessed August 2022
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticHTMLController {
    @GetMapping("/password_reset")
    public String passwordReset() {
        return "password_reset.html";
    }
}
