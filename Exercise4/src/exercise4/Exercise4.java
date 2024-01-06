/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author huanf
 */
public class Exercise4 {


    
    String user;
    String password;
    Scanner sc = new Scanner(System.in);
    ArrayList<Users> userList = new ArrayList<>();
    ArrayList<Integer> packageNumList = new ArrayList <>();
    
    int accountIndex;

    public void initialMenu() {
        boolean initialMenuExit = false;
        int wrongCredential = 0;
        while (!initialMenuExit) {
            System.out.println();
            System.out.println("------------------------------------------------");
            System.out.println("Please indicate the number of the desired option");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int selection;
            selection = sc.nextInt();
            boolean userAuthStatus = false;
            switch (selection) {
                case 1:
                    if (userList.isEmpty()) {
                        System.out.println("There is no registerd users, please register one user");
                        break;
                    }
                    boolean loginExit = false;
                    while (!loginExit) {
                        System.out.println("Please indicate the user name");
                        user = sc.next();
                        System.out.println("Please indicate the password");
                        password = sc.next();
                        userAuthStatus = userAuth(user, password);
                        if (userAuthStatus) {
                            System.out.println("+++++++++++++++++++");
                            System.out.println("User authenticated");
                            System.out.println("+++++++++++++++++++");
                            shippingMenu();
                            initialMenuExit = true;
                            loginExit = true;
                            break;
                        } else {
                            System.out.println("Incorrect user or password");
                            wrongCredential++;
                            if (wrongCredential == 3) {
                                System.out.println("Too many attempts.Exiting system");
                                System.exit(0);
                            }
                        }

                    }
                case 2:
                    System.out.println("Please indicate the user name");
                    user = sc.next();
                    System.out.println("Please indicate the password");
                    password = sc.next();
                    Users newUser = createUser(user, password);
                    if (newUser == null) {
                        throw new NullPointerException("Error in user creation");
                    } else {
                        System.out.println("User successfully registered");
                    }
                    userList.add(newUser);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Incorrect input, please indicate a number from the menu");
                    break;
            }
        }
    }

    public boolean userAuth(String userName, String password) {
        for (Users user : userList) {
            if (user.getUserName().equals(userName)) {
                if (user.getPassword().equals(password)) {
                    accountIndex = userList.indexOf(user);
                    return true;
                }
            }
        }
        return false;
    }

    public void shippingMenu() {
        boolean shippingMenuExit = false;
        while (!shippingMenuExit) {
            int selection = 0;
            System.out.println("-------------------------------------");
            System.out.println("Selecct the transaction");
            System.out.println("1. Send package");
            System.out.println("2. Exit");
            selection = sc.nextInt();
            switch (selection) {
                case 1:
                    boolean exitSend = false;
                    while (!exitSend) {
                        sendPackage();
                        System.out.println("Do you want to sent another package");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int continueOpt = sc.nextInt();
                        switch (continueOpt) {
                            case 1:
                                exitSend = true;
                                break;
                            case 2:
                                System.exit(0);
                        }
                    }

                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Incorrect input, please indicate a number from the menu");
                    break;
            }
        }

    }

    public Users createUser(String userName, String password) {

        try {
            Class<?> userClass = Class.forName("exercise4.Users");
            Constructor<?> userConstructor = userClass.getConstructor(String.class, String.class);
            Object userObject = userConstructor.newInstance(userName, password);
            Users newUser = (Users) userObject;
            return newUser;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

        }
        return null;
    }

    public int packageNumGenerator() {
        Random rnd = new Random();
        boolean exit = false;
        int randomNum = rnd.nextInt(0, 1000);
        while (!exit) {
            for (int number : packageNumList) {
                if (number == randomNum) {
                    break;
                }
            }
            exit = true;
        }
        packageNumList.add(randomNum);
        return randomNum;
    }
        
    public Packages createPackage(int packageNum, String recipient, double totalWeight) {

        try {
            Class<?> userClass = Class.forName("exercise4.Packages");
            Constructor<?> userConstructor = userClass.getConstructor(int.class, String.class, double.class);
            Object packageObject = userConstructor.newInstance(packageNum,recipient, totalWeight);
            Packages newPackage = (Packages) packageObject;
            return newPackage;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

        }
        return null;
    }
       
        
        
        
        public void sendPackage(){
           int packageNum = packageNumGenerator();
            System.out.println("Indicate the recipient name");
            String recipient = sc.next();
            System.out.println("Indicate the total weight of the parcel in kg");
            double totalWeight = sc.nextDouble();
            Packages newPackage = createPackage(packageNum,recipient,totalWeight);
            newPackage.calculateShipping();
            Users sender = userList.get(accountIndex);
            sender.addPackage(newPackage);
            
            
       }
        
    
    public static void main(String[] args) {
        Exercise4 retoInstance = new Exercise4();
        System.out.println("Welcome to the shipping system");
        retoInstance.initialMenu();
    }
    
}
