
package com.bianova.control;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Ingredient {

    public int id;
    public String name;
    public String localizedName;
    public String image;

    /**
     * No args constructor for use in serialization
     */
    public Ingredient() {
    }

    /**
     * @param image
     * @param localizedName
     * @param name
     * @param id
     */
    public Ingredient(int id, String name, String localizedName, String image) {
        super();
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.image = image;
    }

}
