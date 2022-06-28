package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author padrig
 */
public class PrettyPrint {

    private JSONObject json;
    
    public PrettyPrint(JSONObject json){
        json=this.json;
    }
    
    public String getPPJson(JSONObject json){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //JsonParser jp = new JsonParser();
        //JsonElement je = jp.parse(json.getBody().toString());
        String prettyJsonString = gson.toJson(json);
        return prettyJsonString;
    }
}
