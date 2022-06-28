package com.example;

import org.json.JSONObject;

public class App {
    public static void main(String args[]) throws Exception {
        
        GetRecipe gr;
        SearchRecipes sr = new SearchRecipes();
        
        JSONObject recipeList = sr.getList();
        
        PrettyPrint pp = new PrettyPrint(recipeList);
        
        System.out.println(pp.getPPJson(recipeList));
        
        gr = new GetRecipe();
        JSONObject recipe = gr.getId("208094");
        
        System.out.println(pp.getPPJson(recipe));

    }
}