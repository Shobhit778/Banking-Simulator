package com.shobhit.bankingsimulator.model;

import com.shobhit.bankingsimulator.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private long transactionId;
    private long accountNumber;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime transactionDate;

    public Transaction(){
    }

    public Transaction(long transactionId, LocalDateTime transactionDate,long accountNumber, TransactionType transactionType, double amount) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountNumber=" + accountNumber +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }
}
