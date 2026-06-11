package com.shobhit.bankingsimulator.repository;

import com.shobhit.bankingsimulator.model.Transaction;

import java.util.List;

public interface TransactionRepository {
        void save(Transaction transaction);
        List<Transaction> getTransactionHistory(long accountNumber);
}
