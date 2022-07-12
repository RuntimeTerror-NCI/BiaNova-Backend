import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class App {
    public static void main(String args[]) throws Exception {
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
        String params = String.format("query=%s",
        URLEncoder.encode(query, charset));

        // get and response
        HttpResponse<JsonNode> response = Unirest.get(host + "?" + params)
        .header("X-RapidAPI-Host", x_rapidapi_host)
        .header("X-RapidAPI-Key", x_rapidapi_key)
        .asJson();

        // Saving response
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);

        System.out.println(prettyJsonString);
        JSONObject obj = new JSONObject(prettyJsonString);
        //System.out.println(obj.getString("image"));

        //use to get a full array of values eg. all id numbers
        // JSONArray arr = obj.getJSONArray("results"); 
        // for (int i = 0; i < arr.length(); i++)
        // {
        //     int post_id = arr.getJSONObject(i).getInt("id");
        //     System.out.println(post_id);
        // }
    }
} 