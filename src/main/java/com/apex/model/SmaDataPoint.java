package com.apex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmaDataPoint {
    @JsonProperty("SMA")
    private  String sma;

    public String getSma() {
        return sma;
    }
    public void setSma(String sma) {
        this.sma = sma;
    }

    @Override
    public String toString() {
        return "SmaDataPoint{" + "sma='" + sma
                + '\'' + '}';
    }
}
