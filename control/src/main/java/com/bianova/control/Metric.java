package com.bianova.control;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Metric {

    public double amount;
    public String unitShort;
    public String unitLong;

    /**
     * No args constructor for use in serialization
     */
    public Metric() {
    }

    /**
     * @param amount
     * @param unitShort
     * @param unitLong
     */
    public Metric(double amount, String unitShort, String unitLong) {
        super();
        this.amount = amount;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
    }

}
