
package com.bianova.control;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Length {

    public int number;
    public String unit;

    /**
     * No args constructor for use in serialization
     */
    public Length() {
    }

    /**
     * @param number
     * @param unit
     */
    public Length(int number, String unit) {
        super();
        this.number = number;
        this.unit = unit;
    }

}
