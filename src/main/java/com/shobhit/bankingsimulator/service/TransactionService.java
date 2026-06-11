package com.shobhit.bankingsimulator.service;

import com.shobhit.bankingsimulator.enums.TransactionType;
import com.shobhit.bankingsimulator.model.Transaction;
import com.shobhit.bankingsimulator.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private long transactionCounter = 1;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    //Method to record the transaction
    public void recordTransaction(long accountNumber, TransactionType type, double amount){

        Transaction transaction = new Transaction(transactionCounter, LocalDateTime.now(), accountNumber, type, amount);
        transactionCounter++;
        transactionRepository.save(transaction);
    }

    //Method to show the transaction details
    public List<Transaction> showTransactionHistory(long accountNumber){

        return transactionRepository.getTransactionHistory(accountNumber);
    }
}
