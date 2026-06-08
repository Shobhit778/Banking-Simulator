package com.shobhit.bankingsimulator.service;

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

    public void createAccount(Account account){
        account.setAccountNumber(nextAccountNumber);
        nextAccountNumber++;
        account.setBalance(0);
        repository.save(account);
        System.out.println("Account Created Successfully with Account Number: " + account.getAccountNumber());
    }

}
