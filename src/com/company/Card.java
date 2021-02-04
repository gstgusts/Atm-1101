package com.company;

public class Card {
    private int pin;
    private String number;
    private Client client;

    public Card(int pin, String number, Client client) {
        this.pin = pin;
        this.number = number;
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public boolean isPinValid(int pin) {
        return pin == this.pin;
    }
}
