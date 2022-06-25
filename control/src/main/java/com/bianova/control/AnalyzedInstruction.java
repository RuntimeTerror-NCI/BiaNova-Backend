package com.bianova.control;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class AnalyzedInstruction {

    public String name;
    public List<Step> steps = null;

    /**
     * No args constructor for use in serialization
     */
    public AnalyzedInstruction() {
    }

    /**
     * @param name
     * @param steps
     */
    public AnalyzedInstruction(String name, List<Step> steps) {
        super();
        this.name = name;
        this.steps = steps;
    }

}

