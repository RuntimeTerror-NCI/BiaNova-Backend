package com.bianova.control;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Us {

    public double amount;
    public String unitShort;
    public String unitLong;

    /**
     * No args constructor for use in serialization
     */
    public Us() {
    }

    /**
     * @param amount
     * @param unitShort
     * @param unitLong
     */
    public Us(double amount, String unitShort, String unitLong) {
        super();
        this.amount = amount;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
    }

}
