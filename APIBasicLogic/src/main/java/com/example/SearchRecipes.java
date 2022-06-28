package com.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author padrig
 */
public class SearchRecipes {
    
    public SearchRecipes(){
        
    }
    
    public JSONObject getList() throws UnirestException, UnsupportedEncodingException{
        Scanner scan = new Scanner(System.in);
        // Host url
        String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch";
        String charset = "UTF-8";
        
        // Headers for a request
        String x_rapidapi_host = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
        String x_rapidapi_key = "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa";

        //how to add parameters
        System.out.println("Enter your foods here:\n");
        String query = scan.nextLine();
        
        
            /*CUISINE: One or more (comma separated) of the following: 
            african, chinese, japanese, korean, vietnamese, 
            thai, indian, british, irish, french, italian, 
            mexican, spanish, middle eastern, jewish, american, 
            cajun, southern, greek, german, nordic, eastern european, 
            caribbean, or latin american.
            */
        
        String cuisine = "german";
        
            /*DIET: Possible values are: 
            pescetarian, lacto vegetarian, 
            ovo vegetarian, vegan, paleo, 
            primal, and vegetarian.*/
        
        String diet = "";
        String includeIngredients = "mushrooms";
        
            /*RANKING: Whether to: 
            minimize missing ingredients (0), 
            maximize used ingredients (1) first, 
            or rank recipes by relevance (2).
            */
        String ranking = "2";
        
            /*TYPE: One of the following: 
            main course, side dish, dessert, 
            appetizer, salad, bread,
            breakfast, soup, beverage, 
            sauce, or drink.
            */
        String type = "";
        String params = String.format("query=%s&"
                + "cuisine=%s&"
                + "diet=%s"
                + "includeIngredients=%s&"
                + "ranking=%s&"
                + "type=%s",
        URLEncoder.encode(query, charset),
        URLEncoder.encode(cuisine, charset),
        URLEncoder.encode(diet, charset),
        URLEncoder.encode(includeIngredients, charset),
        URLEncoder.encode(ranking, charset),
        URLEncoder.encode(type, charset)
                );

        // get and response
        HttpResponse<JsonNode> response = Unirest.get(host + "?" + params)
        .header("X-RapidAPI-Host", x_rapidapi_host)
        .header("X-RapidAPI-Key", x_rapidapi_key)
        .asJson();
        
        System.out.println(params);

        // Saving response
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);

        //System.out.println(prettyJsonString);
        JSONObject recipeList = new JSONObject(prettyJsonString);
        return recipeList;
    }
}
