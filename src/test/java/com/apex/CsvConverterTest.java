package com.apex;


import com.apex.model.SmaDataPoint;
import com.apex.service.CsvConverter;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvConverterTest {

    @Test
    void shouldWriteSmaDataToCsv() throws Exception {

        Map<String, SmaDataPoint> data = new LinkedHashMap<>();

        SmaDataPoint first = new SmaDataPoint();
        first.setSma("245.31");

        SmaDataPoint second = new SmaDataPoint();
        second.setSma("244.87");

        data.put("2026-10-02", first);
        data.put("2026-10-01", second);

        CsvConverter converter = new CsvConverter();

        Path output =
                Path.of("target/test-output/test-sma.csv");

        converter.writeToCsv(
                data,
                output.toString()
        );

        String csv = Files.readString(output);

        System.out.println(csv);

        assertTrue(csv.contains("date,sma"));
        assertTrue(csv.contains("2026-10-02,245.31"));
        assertTrue(csv.contains("2026-10-01,244.87"));
    }
}
