package com.shobhit.bankingsimulator.model;

import com.shobhit.bankingsimulator.enums.AccountType;

public class Account {

    private String holderName;
    private long accountNumber;
    private String phoneNumber;
    private AccountType accountType;
    private double balance;

    public Account(){
    }

    public Account(AccountType accountType, String phoneNumber, String holderName) {
        this.accountType = accountType;
        this.phoneNumber = phoneNumber;
        this.holderName = holderName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holderName='" + holderName + '\'' +
                ", accountNumber=" + accountNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
