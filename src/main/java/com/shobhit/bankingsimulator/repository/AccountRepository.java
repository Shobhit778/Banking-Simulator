package com.shobhit.bankingsimulator.repository;

import com.shobhit.bankingsimulator.model.Account;

public interface AccountRepository {

    void save(Account account);

    Account findByAccountNumber(long accountNumber);

    void deleteAccount(long accountNumber);

    void update(Account account);

    long getMaxAccountNumber();
}
