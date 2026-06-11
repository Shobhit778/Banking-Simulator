package com.shobhit.bankingsimulator.ui;

import com.shobhit.bankingsimulator.enums.AccountType;
import com.shobhit.bankingsimulator.exception.*;
import com.shobhit.bankingsimulator.model.Account;
import com.shobhit.bankingsimulator.model.Transaction;
import com.shobhit.bankingsimulator.service.AccountService;
import com.shobhit.bankingsimulator.service.TransactionService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class BankMenu {

    Scanner sc = new Scanner(System.in);
    AccountService accountService;
    TransactionService transactionService;

    public BankMenu(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid Input. Please enter a whole number.");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private double readDouble() {
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid Input. Please enter a valid amount.");
            sc.next();
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    private long readLong() {
        while (!sc.hasNextLong()) {
            System.out.println("Invalid Input. Please enter a number.");
            sc.next();
        }
        long value = sc.nextLong();
        sc.nextLine();
        return value;
    }

    private void showMenu(){
        System.out.println("====== WELCOME TO THE BANKING SIMULATOR =====");
        System.out.println("1. CREATE ACCOUNT ");
        System.out.println("2. ACCOUNT DETAILS ");
        System.out.println("3. DEPOSIT ");
        System.out.println("4. WITHDRAW ");
        System.out.println("5. CHECK BALANCE ");
        System.out.println("6. TRANSACTION DETAILS");
        System.out.println("7. DELETE ACCOUNT ");
        System.out.println("8. Exit ");
        System.out.print("Please Enter Your Choice: ");
    }

    private void createAccount(){
        try {
            System.out.println("--- Enter The Following Details ---");
            System.out.print("Please Enter Account Holder Full Name: ");
            String name = sc.nextLine();
            System.out.print("Please Enter Phone Number: ");
            String phoneNumber = sc.nextLine();
            AccountType accountType = null;
            while (true) {
                System.out.println("Please Select The Account Type");
                System.out.println("1. SAVINGS");
                System.out.println("2. CURRENT");
                System.out.print("Enter Your Choice: ");
                int choice = readInt();

                switch (choice) {
                    case 1:
                        accountType = AccountType.SAVINGS;
                        break;
                    case 2:
                        accountType = AccountType.CURRENT;
                        break;
                    default:
                        System.out.println("Please Enter a Valid Choice");
                        continue;
                }
                break;
            }
            Account account = new Account(accountType, phoneNumber, name);
            accountService.createAccount(account);
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAccountDetails() {
        try {
            System.out.print("Enter Account Number: ");
            long accNo = readLong();
            Account account = accountService.getAccountDetails(accNo);
            System.out.println("Account Details");
            System.out.println(account);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposit(){
        try{
            System.out.print("Enter The Account Number: ");
            long accNo = readLong();
            System.out.print("Enter The Amount To Deposit: ");
            double amount = readDouble();
            double balance = accountService.deposit(accNo, amount);
            System.out.println("Amount deposited Successfully ");
            System.out.println("Balance : " + balance);
        }
        catch (InvalidAmountException e){
            System.out.println(e.getMessage());
        }
        catch (InvalidAccountException e){
            System.out.println(e.getMessage());
        }
    }

    private void withdraw(){
        try{
            System.out.print("Enter The Account Number: ");
            long accNo = readLong();
            System.out.print("Enter The Amount To Withdraw: ");
            double amount = readDouble();
            double balance = accountService.withdraw(accNo, amount);
            System.out.println("Amount Withdraw Successful ");
            System.out.println("Balance : " + balance);
        }
        catch (InvalidAmountException e){
            System.out.println(e.getMessage());
        }
        catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
        catch (InvalidAccountException e){
            System.out.println(e.getMessage());
        }
    }

    private void checkBalance(){
        try{
            System.out.print("Enter The Account Number: ");
            long accNo = readLong();
            double balance = accountService.checkBalance(accNo);
            System.out.println("Your Current Balance is : " + balance);
        }
        catch (InvalidAccountException e){
            System.out.println(e.getMessage());
        }
    }

    private void transactionDetails(){
        try{
            System.out.print("Enter The Account Number: ");
            long accNo = readLong();
            List<Transaction> transactionList = transactionService.showTransactionHistory(accNo);
            System.out.println(transactionList);
        }
        catch (TransactionNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteAccount(){
        try{
            System.out.print("Enter The Account Number: ");
            long accNo = readLong();
            accountService.deleteAccount(accNo);
            System.out.println("The Account Got Deleted Successfully");
        }
        catch (InvalidAccountException e){
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        while (true) {

            showMenu();
            int choice = readInt();
            System.out.println();

            switch (choice) {

                case 1:
                    createAccount();
                    System.out.println();
                    break;
                case 2:
                    viewAccountDetails();
                    System.out.println();
                    break;
                case 3:
                    deposit();
                    System.out.println();
                    break;
                case 4:
                    withdraw();
                    System.out.println();
                    break;
                case 5:
                    checkBalance();
                    System.out.println();
                    break;
                case 6:
                    transactionDetails();
                    System.out.println();
                    break;
                case 7:
                    deleteAccount();
                    System.out.println();
                    break;
                case 8:
                    System.out.println("ThankYou! For Using Our Banking Services ");
                    return;
                default:
                    System.out.println("Invalid Choice");
                    System.out.println();
                    break;
            }
        }
    }
}

