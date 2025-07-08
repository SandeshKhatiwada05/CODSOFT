package org.JSP;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public CurrencyConverter(Scanner sc) {
        System.out.println("------ CODSOFT Currency Converter ------");

        System.out.print("Enter base currency (e.g., AUD): ");
        String baseCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., NPR): ");
        String targetCurrency = sc.nextLine().toUpperCase();

        float amount = 0;
        while (true) {
            System.out.print("Enter amount to convert: ");
            if (sc.hasNextFloat()) {
                amount = sc.nextFloat();
                sc.nextLine(); // consume newline
                if (amount <= 0) {
                    System.out.println("Please enter a positive amount.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // clear invalid input
            }
        }

        try {
            String apiKey = "6eeaa8e64688d55adaba6181";
            String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());

            if (!json.getString("result").equals("success")) {
                System.out.println("API Error: " + json.getString("error-type"));
                return;
            }

            JSONObject rates = json.getJSONObject("conversion_rates");
            if (!rates.has(targetCurrency)) {
                System.out.println("Invalid target currency code.");
                return;
            }

            float rate = rates.getFloat(targetCurrency);
            float convertedAmount = amount * rate;

            System.out.println("\n====== Conversion Result ======");
            System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
            System.out.println("===============================\n");

        } catch (Exception e) {
            System.out.println("An error occurred while fetching data.");
            e.printStackTrace();
        }
    }
}
