package com.apex;

import com.apex.client.AlphaVantageClient;
import com.apex.model.SmaResponse;
import com.apex.service.CsvConverter;
import com.apex.service.JsonFileService;
import com.apex.service.SmaParser;

public class Main {

    public static void main(String[] args) {

        String apiKey = System.getenv("ALPHA_VANTAGE_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "ALPHA_VANTAGE_API_KEY is not configured"
            );
        }
        String symbol = args.length > 0
                ? args[0].toUpperCase()
                : "IBM";

        AlphaVantageClient client =
                new AlphaVantageClient();
        JsonFileService jsonFileService = new JsonFileService();
        SmaParser smaParser = new SmaParser();
        CsvConverter csvConverter = new CsvConverter();
        try {
            //1. Fetch sma data from Alpha Vantage
            String json = client.fetchSma(
                    symbol,
                    "daily",
                    10,
                    "close",
                    apiKey
            );
            //2. save raw JSON locally
            String jsonFilePath =
                    "output/" + symbol.toLowerCase() + "_sma.json";
            jsonFileService.saveJson(json,jsonFilePath);
            System.out.println(
                    "JSON saved successfully: " + jsonFilePath
            );
            //3. parse JSON into Java obj
            SmaResponse smaResponse = smaParser.parse(json);
            System.out.println(
                    "Parsed SMA records: "
                            + smaResponse
                            .getTechnicalAnalysis()
                            .size()
            );
            //5. convert Java object to CSV
            String csvFilePath =
                    "output/" + symbol.toLowerCase() + "_sma.csv";

            csvConverter.writeToCsv(
                    smaResponse.getTechnicalAnalysis(),
                    csvFilePath
            );

            System.out.println("CSV saved successfully: " + csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}