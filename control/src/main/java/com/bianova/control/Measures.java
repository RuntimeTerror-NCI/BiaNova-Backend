package com.bianova.control;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Measures {

    public Us us;
    public Metric metric;

    /**
     * No args constructor for use in serialization
     */
    public Measures() {
    }

    /**
     * @param metric
     * @param us
     */
    public Measures(Us us, Metric metric) {
        super();
        this.us = us;
        this.metric = metric;
    }

}
