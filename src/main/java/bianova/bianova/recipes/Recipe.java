package bianova.bianova.recipes;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Recipe implements Serializable {
    @Id
    String id;
    String recipeName;

    public Recipe() {

    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
