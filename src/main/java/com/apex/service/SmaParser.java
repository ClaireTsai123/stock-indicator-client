package com.apex.service;

import com.apex.model.SmaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SmaParser {
    private final ObjectMapper objectMapper;
    public SmaParser() {
        this.objectMapper = new ObjectMapper();
    }
    public SmaResponse parse(String json) throws IOException {
        return objectMapper.readValue(
                json, SmaResponse.class
        );
    }
}
