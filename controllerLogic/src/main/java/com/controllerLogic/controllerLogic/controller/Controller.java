package com.controllerLogic.controllerLogic.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Controller {
    
    @GetMapping(value = "/joke")
    public String chuck(){
        String url = "https://api.chucknorris.io/jokes/random";
        RestTemplate restTemplate = new RestTemplate();
        String joke = restTemplate.getForObject(url, String.class);
        return joke;
    }
    
    @GetMapping("/recipe")
    public RedirectView apiSearch(
      RedirectAttributes attributes) throws UnsupportedEncodingException {
        return new RedirectView("/externalAPI");
    }
    
    @GetMapping("/id")
    public RedirectView apiID(
      RedirectAttributes attributes) throws UnsupportedEncodingException {
        return new RedirectView("/externalAPIid");
    }
}
