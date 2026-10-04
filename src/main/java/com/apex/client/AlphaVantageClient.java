package com.apex.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlphaVantageClient {
    private  static final String BASE_URL =
            "https://www.alphavantage.co/query";
    private final HttpClient httpClient;

    public AlphaVantageClient() {
        this.httpClient = HttpClient.newHttpClient();
    }
    public String fetchSma(
            String symbol,
            String interval,
            int timePeriod,
            String seriesType,
            String apiKey
    ) throws IOException, InterruptedException {

        String url = BASE_URL
                + "?function=SMA"
                + "&symbol=" + symbol
                + "&interval=" + interval
                + "&time_period=" + timePeriod
                + "&series_type=" + seriesType
                + "&apikey=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "API request failed. Status code: "
                            + response.statusCode()
            );
        }

        return response.body();
    }
}
