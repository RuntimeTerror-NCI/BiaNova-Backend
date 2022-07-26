package bianova.bianova.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static java.util.Map.entry;

public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/register";
    public static final Map<String, String> rapidApi = Map.ofEntries(
        entry("X-RapidAPI-Key", "45b936ea7dmsh0e7c737f123e2f8p154c20jsn543d8d833efa"),
        entry("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
    );
}
