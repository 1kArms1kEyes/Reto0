/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author huanf
 */
public class Exercise2 {

    public static String currencyMenu() {
        //Menu to let the user select a currency
        boolean exit = false;

        while (!exit) {

            System.out.println("Please indicate the currency");
            String[] currencies = new String[]{"CLP", "ARS", "USD", "EUR", "TRY", "GBP"};
            int i = 1;
            for (String currency : currencies) {
                String text = i + ". " + currency;
                System.out.println(text);
                i++;
            }
            Scanner sc = new Scanner(System.in);
            int selection = sc.nextInt();
            for (int j = 0; j < currencies.length; j++) {
                if (j == selection - 1) {
                    return currencies[j];
                }
            }
            System.out.println("Currency not found, select a currency from the menu.");
        }
        
        return null;

    }
   
   
    public static boolean amountValidation(List<List<Double>> minMaxAmounts,String currency, double amount) {
        String[] currencies = new String[]{"CLP", "ARS", "USD", "EUR", "TRY", "GBP"};
        int currencyIndex= 0;
        for (int i = 0; i < currencies.length; i++) {
            if(currencies[i].equals(currency)){
                currencyIndex = i;
            }
        }
        double minAmount =minMaxAmounts.get(currencyIndex).get(0);
        double maxAmount =minMaxAmounts.get(currencyIndex).get(1);
        if(minAmount>amount||maxAmount<amount){
            return false;
        }
        return true;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String originCurrency = "";
        String destinationCurrency = "";
        double amount = 0;
        List<String> currencyExchangeInfo = new ArrayList<>();

        //Instance of an example user that has 5000 units of currency for all of the currencies
        User user = new User(5000.0, 5000.0, 5000.0, 5000.0, 5000.0, 5000.0);

        //Creation of a list of lists to store the min and max amount per currency.
        List<List<Double>> minMaxAmounts = new ArrayList<>();

        // Creation of each list (row) that represent a currency and the min and max values for each currency.
        //CLP
        List<Double> clpMinMaxAmounts = new ArrayList<>();

        clpMinMaxAmounts.add(1.0); //Min amount CLP
        clpMinMaxAmounts.add(10_000.0); //Max amount CLP

        //ARS
        List<Double> arsMinMaxAmounts = new ArrayList<>();

        arsMinMaxAmounts.add(1.0); //Min amount ARS
        arsMinMaxAmounts.add(15_000.0); //Max amount ARS        

        //USD
        List<Double> usdMinMaxAmounts = new ArrayList<>();

        usdMinMaxAmounts.add(1.0); //Min amount USD
        usdMinMaxAmounts.add(17_000.0); //Max amount USD          

        //EUR
        List<Double> eurMinMaxAmounts = new ArrayList<>();

        eurMinMaxAmounts.add(1.0); //Min amount EUR
        eurMinMaxAmounts.add(19_000.0); //Max amount EUR

        //TRY
        List<Double> tryMinMaxAmounts = new ArrayList<>();

        tryMinMaxAmounts.add(1.0); //Min amount TRY
        tryMinMaxAmounts.add(7_000.0); //Max amount TRY  

        //GBP
        List<Double> gbpMinMaxAmounts = new ArrayList<>();

        gbpMinMaxAmounts.add(1.0); //Min amount ARS
        gbpMinMaxAmounts.add(15_000.0); //Max amount ARS  

        //Addition of the currencies list to the mixMaxAmounts list
        minMaxAmounts.add(clpMinMaxAmounts);
        minMaxAmounts.add(arsMinMaxAmounts);
        minMaxAmounts.add(usdMinMaxAmounts);
        minMaxAmounts.add(eurMinMaxAmounts);
        minMaxAmounts.add(tryMinMaxAmounts);
        minMaxAmounts.add(gbpMinMaxAmounts);

        //Main Menu
        boolean exit = false;

        while (!exit) {
            System.out.println("----------------------------------------");
            System.out.println("Welcome to the currency exchange system");
            System.out.println("Please select and option");
            System.out.println("1. Indicate origin currency");
            System.out.println("2. Indicate destination currency");
            System.out.println("3. Exchange currency");
            System.out.println("4. Exit");
            System.out.println("----------------------------------------");

            int selection = sc.nextInt();

            switch (selection) {

                case 1:
                    System.out.println("Select the origin currency");
                    originCurrency = currencyMenu();


                    boolean validateAmountExit = false;

                    while (!validateAmountExit) {
                        System.out.println("Indicate the amount to exchage");
                        amount = sc.nextDouble();
                        boolean validatedAmount = amountValidation(minMaxAmounts, originCurrency, amount);

                        if (!validatedAmount) {
                            System.out.println("The amount do not correspond with the defined limits, please change the amount");
                        } else {
                            validateAmountExit = true;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Select the destination currency");
                    destinationCurrency = currencyMenu();
                    break;
                case 3:
                    currencyExchangeInfo = user.exchangeCurrency(originCurrency, destinationCurrency, amount);
                    boolean withdrawExit = false;
                    while (!withdrawExit) {
                        System.out.println("Do you want to Withdraw the converted amount?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int withdrawOpt = sc.nextInt();
                        switch (withdrawOpt) {
                            case 1:

                                user.withdraw(user, originCurrency, destinationCurrency, amount, Double.parseDouble(currencyExchangeInfo.get(0)), Double.parseDouble(currencyExchangeInfo.get(1)));
                                boolean exitAdditionalOpt = false;
                                while (!exitAdditionalOpt) {
                                    System.out.println("Do you want to execute another transaction?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int additionalOpt = sc.nextInt();
                                    switch (additionalOpt) {
                                        case 1:
                                            exitAdditionalOpt = true;
                                            break;
                                        case 2:
                                            System.exit(0);
                                        default:
                                            System.out.println("Incorrect Option, select an option from the menu");
                                    }

                                }

                                withdrawExit = true;
                                break;
                            case 2:
                                withdrawExit = true;
                                break;
                            default:
                                System.out.println("Incorrect Option");
                        }
                    }
                case 4:
                   System.exit(0);
                default:
                    System.out.println("Incorrect option, select an option from the menu");
            }
        }
    }
}
