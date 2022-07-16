
package bianova.bianova.recipes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author padrig
 */
public class APIString {

    public APIString() throws UnsupportedEncodingException {
        
    }
    public String getURL() throws UnsupportedEncodingException{
        String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch";
        String charset = "UTF-8";


        //how to add parameters
        String query = "cheese";
        
        
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
        
        String URL = (host + "?" + params
                //+ x_rapidapi_host + x_rapidapi_key
                );
        //System.out.println(URL);
        return URL;
    }
    
    public String getURLparams() throws UnsupportedEncodingException{
        String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch";
        
        // get and response
        
        String URL = (host + "?" 
                //+ params
                //+ x_rapidapi_host + x_rapidapi_key
                );
        //System.out.println(URL);
        return URL;
    }
    
    public String getIDURL(String ID){
        String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/";
        String URL = (host + ID + "/information");
        return URL;
        
        
    }
    
    public String getRandomURL(){
        String host = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random";
        String URL = (host);
        return URL;
    }
}
