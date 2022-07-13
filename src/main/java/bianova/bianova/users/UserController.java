package bianova.bianova.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users/create_role")
    public ResponseEntity<Role> createRole(@PathVariable String name) {
        Role role = new Role(name);
        userService.createRole(role);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @GetMapping("/users/find_user/{username}")
    public ResponseEntity<User> findUser(@PathVariable String username) {
        try {
            User user = userService.findUser(username);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoUserFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        return new ResponseEntity<String>("registered", HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        return new ResponseEntity<String>("profile", HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> editProfile() {
        return new ResponseEntity<String>("profile edited", HttpStatus.OK);
    }

    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteProfile() {
        return new ResponseEntity<String>("profile deleted", HttpStatus.OK);
    }
}
