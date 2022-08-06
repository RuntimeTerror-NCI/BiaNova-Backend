package bianova.bianova.users;

import bianova.bianova.recipes.Recipe;
import bianova.bianova.security.SecurityConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;

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
    public ResponseEntity<String> saveRecipe(@RequestBody HashMap<String, HashMap<String, String>> json) {
        // reference: https://dzone.com/articles/how-to-get-current-logged-in-username-in-spring-se accessed August 2022
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashMap<String, String> raw_recipe = json.get("recipe");
        Recipe recipe = new Recipe(Integer.parseInt(raw_recipe.get("id")),
            raw_recipe.get("title"),
            raw_recipe.get("img")
            );
        User user = userService.findUser(username);
        if (user == null) {
            return new ResponseEntity<>(
                String.format("error: the user %s was not found", username),
                HttpStatus.BAD_REQUEST
            );
        }
        Collection<Recipe> recipes = user.getSavedRecipesObjects();
//        System.out.println("saving recipe");
//        System.out.println(recipe.getId());
//        System.out.println(recipe.getImg());
//        System.out.println(recipe.getTitle());
        user.addRecipe(recipe);
        userService.updateUser(user);
        return new ResponseEntity<>("recipe saved", HttpStatus.OK);
    }

    @DeleteMapping("/delete_recipe")
    public ResponseEntity<String> deleteRecipe(@RequestBody HashMap<String, Integer> json) {
        int recipeID = json.get("recipeID");
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUser(username);
        user.deleteRecipe(recipeID);
        userService.updateUser(user);
        return new ResponseEntity<>("recipe deleted", HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestParam("username") String username) throws IOException {
        User user = userService.findUser(username);
        if (user == null) {
            return new ResponseEntity<>(
                String.format("error: the user %s was not found", username),
                HttpStatus.BAD_REQUEST
            );
        }
//        System.out.println(user.getSavedRecipesObjects());
        user.setPassword("");
        for (Recipe rec: user.getSavedRecipesObjects()) {
            System.out.println(rec.getId());
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> editProfile(@RequestBody User data) {
//        System.out.println("json + " + data.getUsername());
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

    @GetMapping("/reset_email")
    public ResponseEntity<String> resetEmail(@RequestParam("username") String username) {
        User user = userService.findUser(username);
        String email = user.getEmail();
        int expiry_minutes = (int) SecurityConstants.EMAIL_EXPIRATION_TIME/1000/60;
        String token = JWT.create()
            .withSubject(
                username
            )
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EMAIL_EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        emailService.sendMail(email, "BiaNova - Reset Your Password", "Please use the following link to reset your email \n"
            + SecurityConstants.BASE_URL + "/password_reset?token=" + token
            + "&username=" + username
            + "\nthis link will expire in " + Integer.toString(expiry_minutes) + " minutes");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/set_password")
    public ResponseEntity<Object> setPassword(@RequestBody HashMap<String, String> json) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUser(username);
        User userWithNewPassword = userService.encryptAndSetPassword(user, json.get("password"));
        userService.updateUser(userWithNewPassword);
        HashMap response = new HashMap();
        response.put("Status", "Success");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
