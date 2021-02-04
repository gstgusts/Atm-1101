package com.company;

public class BankAccount {
    private String number;
    private double balance;
    private Client owner;

    public BankAccount(String number, double balance, Client owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public Client getOwner() {
        return owner;
    }

    public boolean takeMoney(double amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        }

        return false;
    }
}
