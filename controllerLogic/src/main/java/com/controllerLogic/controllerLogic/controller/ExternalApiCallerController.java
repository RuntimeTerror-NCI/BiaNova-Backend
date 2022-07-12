package com.controllerLogic.controllerLogic.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExternalApiCallerController {

    @RequestMapping(value="/externalAPI" , method={RequestMethod.GET,RequestMethod.POST})    
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> callExternalApi() throws UnsupportedEncodingException {
        APIString api = new APIString();
        String url = api.getURL();
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa");
        headers.add("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
        HttpEntity<Object> entity=new HttpEntity<Object>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response;   
    }
    
    @RequestMapping(value="/externalAPIid" , method={RequestMethod.GET,RequestMethod.POST}) 
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> callExternalAPIRecipe() throws UnsupportedEncodingException {
        APIString api = new APIString();
        String url = api.getIDURL("479101");
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa");
        headers.add("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
        HttpEntity<Object> entity=new HttpEntity<Object>(headers);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response;
    }

}