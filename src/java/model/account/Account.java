/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.util.ArrayList;

/**
 *
 * @author Ducky
 */
public class Account {
    private String username;
    private String password;
    private String displayName;
    private String email;
    private String phone;
    private ArrayList<Feature> features = new ArrayList<>();
    public Account() {
    }

    public Account(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }
    
        
}
