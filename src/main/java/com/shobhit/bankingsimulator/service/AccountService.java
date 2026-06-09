package com.shobhit.bankingsimulator.service;

import com.shobhit.bankingsimulator.exception.InsufficientBalanceException;
import com.shobhit.bankingsimulator.exception.InvalidAmountException;
import com.shobhit.bankingsimulator.model.Account;
import com.shobhit.bankingsimulator.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository repository;
    private long nextAccountNumber=1001;

    @Autowired
    public AccountService(AccountRepository repository){
        this.repository = repository;
    }

    //Account Create method
    public void createAccount(Account account){
        account.setAccountNumber(nextAccountNumber);
        nextAccountNumber++;
        account.setBalance(0);
        repository.save(account);
        System.out.println("Account Created Successfully with Account Number: " + account.getAccountNumber());
    }

    //Account details fetch method
    public Account getAccountDetails(long accountNumber){
        return repository.findByAccountNumber(accountNumber);
    }

    //Amount Deposit method
    public double deposit(long accountNumber, double amount){
        if(amount<=0){
            throw new InvalidAmountException("Deposit amount must be greater than 0");
        }
        Account account = getAccountDetails(accountNumber);
        account.setBalance(account.getBalance() + amount);
        return account.getBalance();
    }

    //Amount Withdraw method
    public double withdraw(long accountNumber, double amount){
        if(amount <= 0){
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }
        Account account = getAccountDetails(accountNumber);
        if(amount > account.getBalance()){
            throw new InsufficientBalanceException("Withdraw amount must be less than or equal to the current balance");
        }
        account.setBalance(account.getBalance() - amount);
        return account.getBalance();
    }

    // Balance check method
    public double checkBalance(long accountNumber){
        return getAccountDetails(accountNumber).getBalance();
    }

    //Account delete method
    public void deleteAccount(long accountNumber){
        Account account = getAccountDetails(accountNumber);
        repository.deleteAccount(accountNumber);
    }
}
