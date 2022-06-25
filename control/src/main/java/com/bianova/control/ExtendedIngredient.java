package com.bianova.control;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ExtendedIngredient {

    public int id;
    public String aisle;
    public String image;
    public String consistency;
    public String name;
    public String nameClean;
    public String original;
    public String originalName;
    public double amount;
    public String unit;
    public List<String> meta = null;
    public Measures measures;

    /**
     * No args constructor for use in serialization
     */
    public ExtendedIngredient() {
    }

    /**
     * @param originalName
     * @param image
     * @param amount
     * @param unit
     * @param measures
     * @param nameClean
     * @param original
     * @param meta
     * @param name
     * @param id
     * @param aisle
     * @param consistency
     */
    public ExtendedIngredient(int id, String aisle, String image, String consistency, String name, String nameClean, String original, String originalName, double amount, String unit, List<String> meta, Measures measures) {
        super();
        this.id = id;
        this.aisle = aisle;
        this.image = image;
        this.consistency = consistency;
        this.name = name;
        this.nameClean = nameClean;
        this.original = original;
        this.originalName = originalName;
        this.amount = amount;
        this.unit = unit;
        this.meta = meta;
        this.measures = measures;
    }

}
