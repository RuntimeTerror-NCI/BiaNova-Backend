package bianova.bianova;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("sup", HttpStatus.OK);
    }

    @GetMapping("/searchRecipes")
    public ResponseEntity<String> searchRecipes() {
        return new ResponseEntity<String>("recipes", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRecipe() {
        return new ResponseEntity<String>("saved", HttpStatus.OK);
    }
}