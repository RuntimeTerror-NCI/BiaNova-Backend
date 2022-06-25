package com.bianova.control;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    public Recipe findRecipeByTitle(String title);
}
