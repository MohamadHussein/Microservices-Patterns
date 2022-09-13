package com.example.account.reports.adapter.repository;

import com.example.account.reports.model.Account;
import com.example.bank.account.model.AccountType;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface AccountReportsRepo {
    Integer accountsCountByType(AccountType accountType);

    List<String> activeAccountOwnersNames();

    Integer totalActiveAccountsCount();

    void newAccountCreated(Account account);

}
