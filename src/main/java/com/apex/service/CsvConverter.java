package com.apex.service;

import com.apex.model.SmaDataPoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class CsvConverter {

    public void writeToCsv(
            Map<String, SmaDataPoint> data,
            String filePath
    ) throws IOException {

        StringBuilder csv = new StringBuilder();

        csv.append("date,sma\n");

        data.forEach((date, smaDataPoint) ->
                csv.append(date)
                        .append(",")
                        .append(smaDataPoint.getSma())
                        .append("\n")
        );

        Path path = Path.of(filePath);

        Path parent = path.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(path, csv.toString());
    }
}
