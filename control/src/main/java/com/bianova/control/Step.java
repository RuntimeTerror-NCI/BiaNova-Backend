package com.bianova.control;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Step {

    public int number;
    public String step;
    public List<Ingredient> ingredients = null;
    public List<Equipment> equipment = null;
    public Length length;

    /**
     * No args constructor for use in serialization
     */
    public Step() {
    }

    /**
     * @param number
     * @param length
     * @param ingredients
     * @param equipment
     * @param step
     */
    public Step(int number, String step, List<Ingredient> ingredients, List<Equipment> equipment, Length length) {
        super();
        this.number = number;
        this.step = step;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.length = length;
    }

}
