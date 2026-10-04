package com.apex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmaResponse {
    @JsonProperty("Technical Analysis: SMA")
    private Map<String, SmaDataPoint> technicalAnalysis;

    public Map<String, SmaDataPoint> getTechnicalAnalysis() {
        return technicalAnalysis;
    }
    public void setTechnicalAnalysis(
            Map<String, SmaDataPoint> technicalAnalysis
    ) {
        this.technicalAnalysis = technicalAnalysis;
    }
}
