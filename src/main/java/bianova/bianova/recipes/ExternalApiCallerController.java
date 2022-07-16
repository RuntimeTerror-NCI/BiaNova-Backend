package bianova.bianova.recipes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
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
    
    @RequestMapping(value="/externalAPIidlist" , method={RequestMethod.GET,RequestMethod.POST})    
    @PostMapping
    @ResponseBody
    public ArrayList<Integer> callExternalApi2() throws UnsupportedEncodingException {
        //JsonParser springParser = (JsonParser) JsonParserFactory.getJsonParser();
        ObjectMapper objectMapper = new ObjectMapper();
        APIString api = new APIString();
        String url = api.getURL();
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa");
        headers.add("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
        HttpEntity<Object> entity=new HttpEntity<Object>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);

        System.out.println(prettyJsonString);
        JSONObject obj = new JSONObject(prettyJsonString);
        //System.out.println(obj.getString("image"));

        //use to get a full array of values eg. all id numbers
        ArrayList<Integer> IDs = new ArrayList<Integer>();
         JSONArray arr = obj.getJSONArray("results"); 
         for (int i = 0; i < arr.length(); i++)
         {
             int post_id = arr.getJSONObject(i).getInt("id");
             System.out.println(post_id);
             IDs.add(post_id);
         }
        return IDs;
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
    
    @RequestMapping(value="/externalAPIrandom" , method={RequestMethod.GET,RequestMethod.POST})    
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> callExternalApiRandom() throws UnsupportedEncodingException {
        APIString api = new APIString();
        String url = api.getRandomURL();
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa");
        headers.add("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
        HttpEntity<Object> entity=new HttpEntity<Object>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response;   
    }

}