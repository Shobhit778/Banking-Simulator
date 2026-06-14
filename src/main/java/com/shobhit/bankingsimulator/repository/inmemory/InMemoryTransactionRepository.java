package com.shobhit.bankingsimulator.repository.inmemory;

import com.shobhit.bankingsimulator.exception.TransactionNotFoundException;
import com.shobhit.bankingsimulator.model.Transaction;
import com.shobhit.bankingsimulator.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    HashMap<Long, List<Transaction>> transactions = new HashMap<>();

    @Override
    public void save(Transaction transaction){

        if(!transactions.containsKey(transaction.getAccountNumber())){
            transactions.put(transaction.getAccountNumber(), new ArrayList<>());
        }
            transactions.get(transaction.getAccountNumber()).add(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory(long accountNumber){
        if(!transactions.containsKey(accountNumber)){
            throw new TransactionNotFoundException("No Transaction Found");
        }
        return transactions.get(accountNumber);
    }

    @Override
    public long getMaxTransactionId(){
        long max = 1;
        for(Long accountNumber : transactions.keySet()){
            if(accountNumber > max){
                max = accountNumber;
            }
        }
        return max;
    }
}
