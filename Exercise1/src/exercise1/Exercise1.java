/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author huanf
 */
public class Exercise1 {

    String user;
    String password;
    Scanner sc = new Scanner(System.in);
    ArrayList<Users> userList = new ArrayList<>();
    ArrayList<Accounts> accountsList = new ArrayList<>();
    int accountNum = 0;
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
                        System.out.println("There is no users registered, please register at least one user");
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
                            accountMenu();
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
                        throw new NullPointerException("Error en la creación del usuario");
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

    public void accountMenu() {
        boolean accountMenuExit = false;
        while (!accountMenuExit) {
            int selection = 0;
            System.out.println("-------------------------------------");
            System.out.println("Selecct the transaction");
            System.out.println("1. View balance");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw money");
            System.out.println("4. Transfer money");
            System.out.println("5. List accounts");
            System.out.println("6. Exit");
            selection = sc.nextInt();
            switch (selection) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    listAccounts();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Incorrect input, please indicate a number from the menu");
                    break;
            }
        }

    }

    public Users createUser(String userName, String password) {
        Accounts newAccount = createAccount();
        if (newAccount == null) {
            throw new NullPointerException("Error en la creación de la cuenta");
        }

        try {
            Class<?> userClass = Class.forName("exercise1.Users");
            Constructor<?> userConstructor = userClass.getConstructor(String.class, String.class, Accounts.class);
            Object userObject = userConstructor.newInstance(userName, password, newAccount);
            Users newUser = (Users) userObject;
            return newUser;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Accounts createAccount() {
        accountNum++;
        try {
            Class<?> userAccountClass = Class.forName("exercise1.Accounts");
            Constructor<?> accountsConstructor = userAccountClass.getConstructor(int.class);
            Object accountObject = accountsConstructor.newInstance(accountNum);
            Accounts newAccount = (Accounts) accountObject;
            accountsList.add(newAccount);
            return newAccount;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

        }
        return null;
    }

    public void viewBalance() {
        Users userBalance = userList.get(accountIndex);
        String balance = String.valueOf(userBalance.getAccount().getBalance());
        System.out.println("Current balance: USD " + balance);
    }

    public void deposit() {
        System.out.println("Indicate the amout to deposit");
        double deposit = sc.nextDouble();
        Users userDeposit = userList.get(accountIndex);
        double balance = userDeposit.getAccount().getBalance();
        userDeposit.getAccount().setBalance(deposit + balance);
        viewBalance();
    }

    public void withdraw() {

        boolean exit = false;

        while (!exit) {
            System.out.println("Indicate the amount to withdraw");
            double withdrawAmt = sc.nextDouble();
            Users userWithdraw = userList.get(accountIndex);
            double balance = userWithdraw.getAccount().getBalance();
            if (balance < withdrawAmt) {
                System.out.println("The amount to withdraw is superior to the balance, please indicate another amount");
            } else {
                userWithdraw.getAccount().setBalance(balance - withdrawAmt);
                viewBalance();
                exit = true;
            }
        }

    }

    public void transfer() {
        Accounts transferAccount = null;
        boolean validAccount = false;
        System.out.println("Indicate the recipient account number");
        while (!validAccount) {

            int accountNum = sc.nextInt();
            for (Accounts account : accountsList) {
                if (account.getAccountNum() == accountNum) {
                    validAccount = true;
                    transferAccount = account;
                    break;
                }
            }
            if (!validAccount) {
                System.out.println("Account not found, select another account.");
            }
        }
        boolean validAmount = false;
        while (!validAmount) {
            System.out.println("Indicate the amount to transfer");
            double transferAmt = sc.nextDouble();
            Users sender = userList.get(accountIndex);
            double senderBalance = sender.getAccount().getBalance();
            if (senderBalance < transferAmt) {
                System.out.println("The amount to transfer is superior to the current balance of the account");
            } else {
                sender.getAccount().setBalance(senderBalance - transferAmt);
                transferAccount.setBalance(transferAccount.getBalance() + transferAmt);
                System.out.println("Succesful transfer");
                validAmount = true;
            }

        }

    }

    public void listAccounts() {
        for (Accounts account : accountsList) {
            System.out.println(account.getAccountNum());
        }
    }

    public static void main(String[] args) {
        Exercise1 retoInstance = new Exercise1();
        System.out.println("Welcome to the banking system");
        retoInstance.initialMenu();

    }

}
