/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise4;

import java.util.ArrayList;

/**
 *
 * @author huanf
 */
public class Users {
    private String userName;
    private String password;
    private ArrayList <Packages> packagesSent;
    
    //Constructor

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
        packagesSent = new ArrayList<>();
    }
    
    //Getters & Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Packages> getPackagesSent() {
        return packagesSent;
    }
    
    //Method

    public void addPackage(Packages newPackage) {
        packagesSent.add(newPackage);
    }
    
   
}
