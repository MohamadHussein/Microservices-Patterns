package com.example.account.reports.queryhandlers;

import com.example.account.reports.adapter.repository.AccountReportsRepo;
import com.example.bank.account.common.query.OwnerNamesQuery;
import com.example.bank.account.common.query.TotalAccountCountByTypeQuery;
import com.example.bank.account.common.query.TotalAccountCountQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountQueryHandler {

    private final AccountReportsRepo accountReportsRepo;


    public AccountQueryHandler(AccountReportsRepo accountReportsRepo) {
        this.accountReportsRepo = accountReportsRepo;
    }

    @QueryHandler
    public List<String> handleOwnerNames(OwnerNamesQuery ownerNamesQuery){
        return accountReportsRepo.activeAccountOwnersNames();
    }

    @QueryHandler
    public Integer handleTotalAccountCountQuery(TotalAccountCountQuery totalAccountCountQuery){
        return accountReportsRepo.totalActiveAccountsCount();
    }

    @QueryHandler
    public Integer handleTotalAccountCountByTypeQuery(TotalAccountCountByTypeQuery typeQuery){
        return accountReportsRepo.accountsCountByType(typeQuery.getAccountType());
    }
}
