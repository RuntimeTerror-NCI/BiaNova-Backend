package com.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
public class GetRecipe {
    
    public GetRecipe(){
    
    }
    public  JSONObject getId(String id) throws UnirestException{
        //Scanner scan = new Scanner(System.in);
        // Host url
        String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/";
        
        // Headers for a request
        String x_rapidapi_host = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
        String x_rapidapi_key = "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa";
        
        String params = host + id + "/information";
        //System.out.println(params);


        // get and response
        HttpResponse<JsonNode> response = Unirest.get(params)
        .header("X-RapidAPI-Host", x_rapidapi_host)
        .header("X-RapidAPI-Key", x_rapidapi_key)
        .asJson();

        // Saving response
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);
        JSONObject recipe = new JSONObject(prettyJsonString);

        return recipe;
    }
}
