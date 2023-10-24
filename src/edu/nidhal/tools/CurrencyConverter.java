/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.tools;

/**
 *
 * @author nidha
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) {
        String apiKey = "4f96cde91a626c11f5247262ec6a26a0";  // Replace with your actual API key
        double amountInTND = 100.0;  // Replace with the amount you want to convert
        String fromCurrency = "TND";
        String toCurrency = "USD";

        try {
            String apiUrl = "https://api.apilayer.com/exchangerates_data/latest?base=" + fromCurrency + "&symbols=" + toCurrency;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();

                // Parse the JSON response
                double exchangeRate = parseExchangeRate(response, toCurrency);
                double amountInUSD = amountInTND * exchangeRate;

                System.out.println(amountInTND + " " + fromCurrency + " is approximately " + amountInUSD + " " + toCurrency);
            } else {
                System.out.println("API request failed with response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double parseExchangeRate(String response, String toCurrency) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject rates = jsonObject.getJSONObject("rates");
            return rates.getDouble(toCurrency);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;  // Return a negative value to indicate an error
        }
    }
}
