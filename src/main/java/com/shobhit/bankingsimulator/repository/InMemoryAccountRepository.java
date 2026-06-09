package com.shobhit.bankingsimulator.repository;

import com.shobhit.bankingsimulator.exception.AccountAlreadyExistsException;
import com.shobhit.bankingsimulator.exception.InvalidAccountException;
import com.shobhit.bankingsimulator.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryAccountRepository implements AccountRepository{
    private HashMap<Long, Account> accounts = new HashMap<>();

@Override
public void save(Account account){
    if(account == null){
        throw new InvalidAccountException("Account cannot be null");
    }
    if(accounts.containsKey(account.getAccountNumber())) {
        throw new AccountAlreadyExistsException("Account already exists with this Account Number: " + account.getAccountNumber());
    }
    accounts.put(account.getAccountNumber(), account);
}

@Override
public Account findByAccountNumber(long accountNumber){
    if(!accounts.containsKey(accountNumber)){
        throw new InvalidAccountException("Account Not Found");
    }
    return accounts.get(accountNumber);
}

@Override
public void deleteAccount(long accountNumber){
    accounts.remove(accountNumber);
}

}
