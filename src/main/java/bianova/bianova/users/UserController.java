package bianova.bianova.users;

import bianova.bianova.recipes.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
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
    public ResponseEntity<String> register(@RequestBody User newUser) {
        if (userService.findUser(newUser.getUsername()) == null) {
            userService.createUser(newUser);
            return new ResponseEntity<>(
                String.format("user %s created successfully", newUser.getUsername()),
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                String.format("a user with the username %s already exists", newUser.getUsername()),
                HttpStatus.CONFLICT
            );
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRecipe(@RequestBody Map<String,  String> json) {
        String username = json.get("username");
        String recipe = json.get("recipe");
        ObjectMapper objectMapper = new ObjectMapper();

        User user = userService.findUser(username);
        if (user == null) {
            return new ResponseEntity<>(
                String.format("error: the user %s was not found", username),
                HttpStatus.BAD_REQUEST
            );
        }
        user.addRecipe(recipe);
        userService.updateUser(user);
        return new ResponseEntity<>("recipe saved", HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestBody Map<String, String> json) {
        String username = json.get("username");
        User user = userService.findUser(username);
        if (user == null) {
            return new ResponseEntity<>(
                String.format("error: the user %s was not found", username),
                HttpStatus.BAD_REQUEST
            );
        }
        user.setPassword("");
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @PutMapping("/profile")
    public ResponseEntity<User> editProfile(@RequestBody User data) {
        System.out.println("json + " + data.getUsername());
        User oldUser = userService.findUserById(data.getId());
        // we don't want to let the user to change their password
        // so the app sends an empty string to the frontend
        data.setPassword(oldUser.getPassword());
        User savedProfile = userService.updateProfile(data);
        if (savedProfile != null) {
            // remove the password again
            savedProfile.setPassword("");
            return new ResponseEntity<User>(savedProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteProfile() {
        return new ResponseEntity<String>("profile deleted", HttpStatus.OK);
    }
}
