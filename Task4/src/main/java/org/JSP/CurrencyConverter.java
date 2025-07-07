package org.JSP;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public CurrencyConverter() {
        Scanner sc = new Scanner(System.in);

        System.out.println("CODSOFT's Currency Convertor");

        System.out.print("Enter Currency you want to convert(e.g., AUD): ");
        String baseCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter currency you want to convert (e.g., NPR): ");
        String targetCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter Amount to Convert: ");
        float amount = sc.nextFloat();

        try {
            // API key and URL
            String apiKey = "6eeaa8e64688d55adaba6181";
            String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            // Open connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Parse JSON
            JSONObject json = new JSONObject(response.toString());

            if (!json.getString("result").equals("success")) {
                System.out.println("API Error: " + json.getString("error-type"));
                return;
            }

            JSONObject rates = json.getJSONObject("conversion_rates");
            if (!rates.has(targetCurrency)) {
                System.out.println("Invalid Target Currency.");
                return;
            }

            float rate = rates.getFloat(targetCurrency);
            float convertedAmount = amount * rate;

            System.out.printf("\n%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("An error occurred while fetching data.");
            e.printStackTrace();
        }
        sc.close();
    }
}
