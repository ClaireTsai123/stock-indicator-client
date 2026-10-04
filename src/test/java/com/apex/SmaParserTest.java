package com.apex;

import com.apex.model.SmaResponse;
import com.apex.service.SmaParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmaParserTest {
    @Test
    void shouldParseSmaJson() throws Exception {
        String json = Files.readString(
                Path.of("src/test/resources/sample-sma-response.json")
        );
        SmaParser parser = new SmaParser();
        SmaResponse response = parser.parse(json);
        assertNotNull(response);
        assertNotNull(response.getTechnicalAnalysis());
        assertEquals(
                2,
                response.getTechnicalAnalysis().size()
        );

        assertEquals(
                "245.31",
                response.getTechnicalAnalysis()
                        .get("2026-10-02")
                        .getSma()
        );
    }
}
