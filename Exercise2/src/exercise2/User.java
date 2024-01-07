/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise2;

import exercise2.ExchangeRate;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

/**
 *
 * @author huanf
 */
public class User {
    private double balanceCLP;
    private double balanceARS;
    private double balanceUSD;
    private double balanceEUR;
    private double balanceTRY;
    private double balanceGBP;
    
    //Constructor

    public User(double balanceCLP, double balanceARS, double balanceUSD, double balanceEUR, double balanceTRY, double balanceGBP) {
        this.balanceCLP = balanceCLP;
        this.balanceARS = balanceARS;
        this.balanceUSD = balanceUSD;
        this.balanceEUR = balanceEUR;
        this.balanceTRY = balanceTRY;
        this.balanceGBP = balanceGBP;
    }

   
    
    //Getters & Setters

    public double getBalanceCLP() {
        return balanceCLP;
    }

    public void setBalanceCLP(double balanceCLP) {
        this.balanceCLP = balanceCLP;
    }

    public double getBalanceARS() {
        return balanceARS;
    }

    public void setBalanceARS(double balanceARS) {
        this.balanceARS = balanceARS;
    }

    public double getBalanceUSD() {
        return balanceUSD;
    }

    public void setBalanceUSD(double balanceUSD) {
        this.balanceUSD = balanceUSD;
    }

    public double getBalanceEUR() {
        return balanceEUR;
    }

    public void setBalanceEUR(double balanceEUR) {
        this.balanceEUR = balanceEUR;
    }

    public double getBalanceTRY() {
        return balanceTRY;
    }

    public void setBalanceTRY(double balanceTRY) {
        this.balanceTRY = balanceTRY;
    }

    public double getBalanceGBP() {
        return balanceGBP;
    }

    public void setBalanceGBP(double balanceGBP) {
        this.balanceGBP = balanceGBP;
    }
    
    
    //Methods
    public List<String> exchangeCurrency(String originCurrency, String destinationCurrency, double amount) {
        //Method for the calculation of the exchange rate.
        double balance = 0;
        //Switch for determining the correct balance attribute that the user have selected.
        switch (originCurrency) {
            case "CLP":
                balance = balanceCLP;
                break;
            case "ARS":
                balance = balanceARS;
                break;
            case "USD":
                balance = balanceUSD;
                break;
            case "EUR":
                balance = balanceEUR;
                break;
            case "TRY":
                balance = balanceTRY;
                break;

        }
        //Validation that the user have not a zero balance or that the amount requested for exchange is enough to also consider the comission.
        if (balance <= 0 || balance < (amount * 1.01)) {
            System.out.println("You don't have enough origin currency for the transaction");
        }
        //Instance of an exchangeRate object.
        ExchangeRate exchangeRate = new ExchangeRate();

        //Getting the list of rates per currency:
        List<List<String>> currencyValues = exchangeRate.getExchangeValues();

        double rate = 0;
        
        //Identification of the exchange rate depending of the origin currency and destination currency:
        for (List<String> list : currencyValues) {
            
            if (list.get(0).equals(originCurrency)) {
                if (list.get(1).equals(destinationCurrency)) {
                    rate = Double.parseDouble(list.get(2));
                }
            } else if (list.get(1).equals(originCurrency)) {
                
                if (list.get(0).equals(destinationCurrency)) {
                    rate = 1 / Double.parseDouble(list.get(2));
                } else {
                    System.out.println("Currency not found");
                }
            }

        }
        //Currency exhange calculation
        double result = amount * rate;
        //Generation of a list that stores the rate used for the money exchange and the destination currency amount.
        List<String> exchangeResults = new ArrayList<>();
        exchangeResults.add(Double.toString(rate));
        exchangeResults.add(Double.toString(result));
        
        return exchangeResults;
    }

    
    public void withdraw(User user, String originCurrency, String destinationCurrency, double amount, double rate, double result) {
        String attribute = "balance" + originCurrency;
        double balance = 0;
        
        //Dynamic invocation of the getter method according to the origin currency.
        try {
            String getterMethodName = "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            Method getterMethod = user.getClass().getMethod(getterMethodName);
            balance = ((Number) getterMethod.invoke(user)).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Calculation of the comission for the exchange and the net balance of the original currency.
        
        double comission = 0.01;
        double totalDeduction =amount * (1 + comission);
        double netBalance = balance - totalDeduction ;
        
        //Dynamic invocation of the setter method according to the origin currency to set the new balance value.
        try {
            String setterMethodName = "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            Method setterMethod = user.getClass().getMethod(setterMethodName, double.class);
            setterMethod.invoke(user, netBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Print of the transaction's summary.
        System.out.println("--------------------");
        System.out.println("Transaction Summary");
        System.out.println("--------------------");
        System.out.println();
        System.out.println("Origin currency:\t\t"+originCurrency);
        System.out.println("Destination currency:\t\t"+destinationCurrency);
        System.out.println("Amount requested for exchange:\t"+amount+" "+originCurrency);
        System.out.println("Origin currency Balance:\t"+balance+" "+originCurrency);
        System.out.println("Exchange Rate:\t"+rate);
        System.out.println("Comission:\t"+comission);
        System.out.println("Comission Amount:\t"+(amount*comission));
        System.out.println("Total deduction:\t"+totalDeduction);
        System.out.println("New net balance :\t"+netBalance);
        System.out.println("Destinacion currency withdrawn:\t"+result+" "+destinationCurrency);
        System.out.println("--------------------");
        System.out.println();
    }
}
