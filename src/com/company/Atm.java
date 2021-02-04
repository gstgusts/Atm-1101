package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Atm {
    private static final int MAX_AMOUNT_WITHDREW = 450;
    private int balance;
    private List<BankAccount> accounts = new ArrayList<>();
    private Card currentCard;
    private BankAccount currentAccount;
    private boolean pinValid;

    public Atm(int balance, List<BankAccount> accounts) {
        this.balance = balance;
        this.accounts = accounts;
    }

    public boolean isPinValid(int pin) {
        if(currentCard == null) {
            return false;
        }

        pinValid = currentCard.isPinValid(pin);
        return pinValid;
    }

    public void enterCard(Card card) {
        currentCard = card;
    }

    public boolean hasCard() {
        return currentCard != null;
    }

    public boolean pinValidated() {
        return pinValid;
    }

    public Iterable<BankAccount> getBankAccounts() {
        if(!hasCard() || !pinValidated()) {
            return new ArrayList<>();
        }

        var clientId = currentCard.getClient().getCode();

        var result = accounts.stream()
                .filter(a -> a.getOwner().getCode().equalsIgnoreCase(clientId))
                .collect(Collectors.toList());

        return result;
    }

    public void selectAccount(BankAccount account) {
        currentAccount = account;
    }

    public double getBalance() {
        if(currentAccount == null) {
            return 0;
        }
        return currentAccount.getBalance();
    }

    public TakeMoneyResult takeMoney(int amount) {
        if(!hasCard() || !pinValidated() || currentAccount == null) {
            return TakeMoneyResult.ERROR;
        }

        if(balance < amount) {
            return TakeMoneyResult.ATM_NOT_ENOUGH;
        }

        if(currentAccount.takeMoney(amount)) {
            balance -= amount;
            return TakeMoneyResult.SUCCESS;
        }

        return TakeMoneyResult.ACCOUNT_NOT_ENOUGH;
    }

    public void exit() {
        currentAccount = null;
        currentCard = null;
        pinValid = false;
    }
}
