# Stock Indicator Client

A Java application that integrates with the Alpha Vantage public API to fetch stock technical indicators.

The application currently supports **SMA (Simple Moving Average)** data. It saves the raw JSON response locally, parses the response into Java objects using Jackson, and converts the indicator data into CSV format.

## Features

- Fetch stock SMA data from Alpha Vantage
- Support dynamic stock symbols from command-line arguments
- Save raw API responses as JSON files
- Parse JSON responses into Java objects
- Convert SMA data into CSV format
- Use environment variables to protect the API key
- Unit testing with JUnit 5
- Build an executable JAR with Maven

## Tech Stack

- Java 21
- Maven
- Java HttpClient
- Jackson
- JUnit 5
- Alpha Vantage API

## Project Structure

```text
stock-indicator-client/
├── pom.xml
├── README.md
├── .gitignore
├── output/
│   ├── ibm_sma.json
│   └── ibm_sma.csv
└── src/
    ├── main/
    │   └── java/
    │       └── com/apex/
    │           ├── Main.java
    │           ├── client/
    │           │   └── AlphaVantageClient.java
    │           ├── model/
    │           │   ├── SmaResponse.java
    │           │   └── SmaDataPoint.java
    │           └── service/
    │               ├── JsonFileService.java
    │               ├── SmaParser.java
    │               └── CsvConverter.java
    └── test/
        ├── java/
        │   └── com/apex/
        │       ├── SmaParserTest.java
        │       └── CsvConverterTest.java
        └── resources/
            └── sample-sma-response.json
```

## API

This project uses the Alpha Vantage API.

The current implementation fetches:

- Indicator: SMA
- Interval: Daily
- Time Period: 10
- Series Type: Close

Example request:

```text
SMA for IBM using daily closing prices and a 10-period moving average
```

## API Key Configuration

Do not hardcode the API key in the source code.

Set the API key as an environment variable:

```bash
export ALPHA_VANTAGE_API_KEY=your_api_key
```

The application reads it using:

```java
System.getenv("ALPHA_VANTAGE_API_KEY");
```

## Build

Run:

```bash
mvn clean package
```

This runs the tests and creates an executable JAR under:

```text
target/stock-indicator-client-1.0.0.jar
```

## Run

Run the application with a stock symbol:

```bash
java -jar target/stock-indicator-client-1.0.0.jar AAPL
```

You can also use other symbols:

```bash
java -jar target/stock-indicator-client-1.0.0.jar MSFT
```

```bash
java -jar target/stock-indicator-client-1.0.0.jar IBM
```

If no symbol is provided, the application uses IBM by default.

## Output

For example, running:

```bash
java -jar target/stock-indicator-client-1.0.0.jar AAPL
```

generates:

```text
output/aapl_sma.json
output/aapl_sma.csv
```

Example CSV:

```csv
date,sma
2026-10-02,245.31
2026-10-01,244.87
```

## Testing

Run all unit tests with:

```bash
mvn clean test
```

The project includes tests for:

- JSON parsing
- SMA response mapping
- CSV generation

The tests use local sample data instead of calling the external API directly, which keeps the tests reliable and independent of network availability or API rate limits.

## Application Flow

```text
Alpha Vantage API
        ↓
Java HttpClient
        ↓
JSON Response
        ↓
Save JSON Locally
        ↓
Jackson Parser
        ↓
Java Objects
        ↓
CSV Converter
        ↓
CSV Output
```

## Security

The Alpha Vantage API key is stored as an environment variable and is not committed to source control.

## CI/CD

This project uses Jenkins for continuous integration and automated build execution.

The Jenkins pipeline performs the following steps:

```text
Checkout Source Code
        ↓
Verify Java and Maven
        ↓
Run Unit Tests
        ↓
Package Executable JAR
        ↓
Run the Application
        ↓
Archive JAR, JSON, and CSV Artifacts
```

The Alpha Vantage API key is stored securely in Jenkins Credentials and injected into the pipeline as an environment variable.

A successful Jenkins build verifies that the application can compile, pass all tests, generate the executable JAR, fetch SMA data, and produce the JSON and CSV output files.