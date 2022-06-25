package com.bianova.control;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Temperature {

    public double number;
    public String unit;

    /**
     * No args constructor for use in serialization
     */
    public Temperature() {
    }

    /**
     * @param number
     * @param unit
     */
    public Temperature(double number, String unit) {
        super();
        this.number = number;
        this.unit = unit;
    }

}
