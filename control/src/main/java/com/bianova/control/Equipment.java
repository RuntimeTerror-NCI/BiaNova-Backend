package com.bianova.control;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Equipment {

    public int id;
    public String name;
    public String localizedName;
    public String image;
    public Temperature temperature;

    /**
     * No args constructor for use in serialization
     */
    public Equipment() {
    }

    /**
     * @param image
     * @param localizedName
     * @param name
     * @param temperature
     * @param id
     */
    public Equipment(int id, String name, String localizedName, String image, Temperature temperature) {
        super();
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.image = image;
        this.temperature = temperature;
    }
}
