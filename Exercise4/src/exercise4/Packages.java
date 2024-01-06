/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise4;

import java.util.Random;

/**
 *
 * @author huanf
 */
public class Packages {
    private int packageNum;
    private String recipient;
    private final double SHIPPING_PRICE = 2;
    private double totalWeight;
    private double amountToPay;
    
    //Constructor

    public Packages(int packageNum,String recipient, double totalWeight) {
        this.packageNum = packageNum;
        this.recipient = recipient;
        this.totalWeight = totalWeight;
    }
    
    //Getters & Setters

    public int getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(int packageNum) {
        this.packageNum = packageNum;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }
    
    public void calculateShipping (){
        amountToPay = totalWeight*SHIPPING_PRICE;
        setAmountToPay(amountToPay);
        System.out.println("---------------------");
        System.out.println("Shipping Chekout");
        System.out.println("---------------------");
        System.out.println("Package Number: "+packageNum);
        System.out.println("Recipient: "+recipient);
        System.out.println("Shipping price: "+SHIPPING_PRICE+" USD");
        System.out.println("Total Weight: "+totalWeight+ " kg");
        System.out.println("Amount to pay: "+amountToPay+ " USD");
        System.out.println("---------------------");
    
    }
    
    
    
    
    

}
