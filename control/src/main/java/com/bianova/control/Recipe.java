package com.bianova.control;

import org.springframework.data.annotation.Id;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Recipe {

    public boolean vegetarian;
    public boolean vegan;
    public boolean glutenFree;
    public boolean dairyFree;
    public boolean veryHealthy;
    public boolean cheap;
    public boolean veryPopular;
    public boolean sustainable;
    public boolean lowFodmap;
    public int weightWatcherSmartPoints;
    public String gaps;
    public int preparationMinutes;
    public int cookingMinutes;
    public int aggregateLikes;
    public int healthScore;
    public String creditsText;
    public String sourceName;
    public double pricePerServing;
    public List<ExtendedIngredient> extendedIngredients = null;
    @Id
    public String id;
    public String title;
    public int readyInMinutes;
    public int servings;
    public String sourceUrl;
    public int openLicense;
    public String image;
    public String imageType;
    public String summary;
    public List<Object> cuisines = null;
    public List<String> dishTypes = null;
    public List<String> diets = null;
    public List<Object> occasions = null;
    public WinePairing winePairing;
    public String instructions;
    public List<AnalyzedInstruction> analyzedInstructions = null;
    public Object originalId;

    /**
     * No args constructor for use in serialization
     */
    public Recipe() {
    }

    /**
     * @param instructions
     * @param sustainable
     * @param analyzedInstructions
     * @param glutenFree
     * @param veryPopular
     * @param healthScore
     * @param title
     * @param diets
     * @param aggregateLikes
     * @param creditsText
     * @param readyInMinutes
     * @param sourceUrl
     * @param dairyFree
     * @param servings
     * @param vegetarian
     * @param id
     * @param preparationMinutes
     * @param imageType
     * @param winePairing
     * @param summary
     * @param cookingMinutes
     * @param image
     * @param openLicense
     * @param veryHealthy
     * @param vegan
     * @param cheap
     * @param extendedIngredients
     * @param dishTypes
     * @param gaps
     * @param cuisines
     * @param lowFodmap
     * @param weightWatcherSmartPoints
     * @param occasions
     * @param pricePerServing
     * @param sourceName
     * @param originalId
     */
    public Recipe(boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean cheap, boolean veryPopular, boolean sustainable, boolean lowFodmap, int weightWatcherSmartPoints, String gaps, int preparationMinutes, int cookingMinutes, int aggregateLikes, int healthScore, String creditsText, String sourceName, double pricePerServing, List<ExtendedIngredient> extendedIngredients, String id, String title, int readyInMinutes, int servings, String sourceUrl, int openLicense, String image, String imageType, String summary, List<Object> cuisines, List<String> dishTypes, List<String> diets, List<Object> occasions, WinePairing winePairing, String instructions, List<AnalyzedInstruction> analyzedInstructions, Object originalId) {
        super();
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.cheap = cheap;
        this.veryPopular = veryPopular;
        this.sustainable = sustainable;
        this.lowFodmap = lowFodmap;
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
        this.gaps = gaps;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.aggregateLikes = aggregateLikes;
        this.healthScore = healthScore;
        this.creditsText = creditsText;
        this.sourceName = sourceName;
        this.pricePerServing = pricePerServing;
        this.extendedIngredients = extendedIngredients;
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.sourceUrl = sourceUrl;
        this.openLicense = openLicense;
        this.image = image;
        this.imageType = imageType;
        this.summary = summary;
        this.cuisines = cuisines;
        this.dishTypes = dishTypes;
        this.diets = diets;
        this.occasions = occasions;
        this.winePairing = winePairing;
        this.instructions = instructions;
        this.analyzedInstructions = analyzedInstructions;
        this.originalId = originalId;
    }

}
