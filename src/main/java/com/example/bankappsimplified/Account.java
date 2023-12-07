package com.example.bankappsimplified;

// this is going to be the instance that holds the users data once they log in to populate texts

public class Account {

    private double balance;
    private String name;
    private int account_number;
    private String password;
    private int creditScore;
    private String username;

    Account (double balance, String name, int account_number, String password, int creditScore, String username) {
        this.balance = balance;
        this.name = name;
        this.account_number = account_number;
        this.password = password;
        this.creditScore = creditScore;
        this.username = username;
    }

    Account () { // to make an empty class

    }

    // Getters
    public double getBalance() {return this.balance;}
    public String getName() {return this.name;}
    public int getAccount_number() {return this.account_number;}
    public String getPassword() {return this.password;}
    public int getCreditScore() {return this.creditScore;}
    public String getUsername() {return this.username;}

    // Setters
    public void setBalance(double balance) {this.balance = balance;}
    public void setName(String name) {this.name = name;}
    public void setAccount_number(int account_number) {this.account_number = account_number;}
    public void setPassword(String password) {this.password = password;}
    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}
    public void setUsername (String username) {this.username = username;}
}
