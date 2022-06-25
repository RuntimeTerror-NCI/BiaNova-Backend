package com.bianova.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class Controller {

	@Autowired
	private RecipeRepository repository;

	@GetMapping("/delete")
	public String delete() {
		repository.deleteAll();
		return "All Data Was Deleted";
	}

	@GetMapping("/add")
	public String add() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Recipe recipe = objectMapper.readValue(new File("example_api_response.json"), Recipe.class);
		repository.save(recipe);
		return "Recipe Saved";
	}

    @GetMapping("/")
	public Recipe index() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Recipe recipe = objectMapper.readValue(new File("example_api_response.json"), Recipe.class);
		return recipe;
	}
}
