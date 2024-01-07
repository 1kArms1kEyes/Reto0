/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huanf
 */
public class ExchangeRate {
    
    //List oflist attribute that stores the different exchage rates per currency
    private List<List<String>> exchangeValues = new ArrayList<>();

    //Constructor
    public ExchangeRate() {

        /*The for loop creates a List for each currency, a row for each of the data needed: 
            1. Origin currency.
            2. Destination currency.
            3. Exchange rate.
          Then, it adds it to the exchangeValues List of Lists */
        for (int i = 1; i <= 15; i++) {
            List<String> row = new ArrayList<>();

            switch (i) {

                case 1:
                    row.add("CLP");
                    row.add("ARS");
                    row.add("0.91");
                    break;
                case 2:
                    row.add("CLP");
                    row.add("USD");
                    row.add("0.0011");
                    break;
                case 3:
                    row.add("CLP");
                    row.add("EUR");
                    row.add("0.0010");
                    break;
                case 4:
                    row.add("CLP");
                    row.add("TRY");
                    row.add("0.033");
                    break;
                case 5:
                    row.add("CLP");
                    row.add("GBP");
                    row.add("0.00088");
                    break;
                case 6:
                    row.add("ARS");
                    row.add("USD");
                    row.add("0.0012");
                    break;
                case 7:
                    row.add("ARS");
                    row.add("EUR");
                    row.add("0.0011");
                    break;
                case 8:
                    row.add("ARS");
                    row.add("TRY");
                    row.add("0.037");
                    break;
                case 9:
                    row.add("ARS");
                    row.add("GBP");
                    row.add("0.00097");
                    break;
                case 10:
                    row.add("USD");
                    row.add("EUR");
                    row.add("0.91");
                    break;
                case 11:
                    row.add("USD");
                    row.add("TRY");
                    row.add("29.82");
                    break;
                case 12:
                    row.add("USD");
                    row.add("GBP");
                    row.add("0.79");
                    break;
                case 13:
                    row.add("EUR");
                    row.add("TRY");
                    row.add("32.68");
                    break;
                case 14:
                    row.add("EUR");
                    row.add("GBP");
                    row.add("0.79");
                    break;
                case 15:
                    row.add("TRY");
                    row.add("GBP");
                    row.add("0.026");
                    break;
            }
        exchangeValues.add(row);
        }

    }
    
    //Getter

    public List<List<String>> getExchangeValues() {
        return exchangeValues;
    }
    
    
}

