package com.example.account.reports.adapter.repository.impl;

import com.example.account.reports.adapter.repository.AccountReportsRepo;
import com.example.account.reports.model.Account;
import com.example.bank.account.model.AccountType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

@Component
public class AccountReportsInMemImpl implements AccountReportsRepo {

    ConcurrentHashMap<AccountType, Integer> map = new ConcurrentHashMap<AccountType, Integer>() {{
        Stream.of(AccountType.values()).map(accountType -> put(accountType, 0));
        System.out.println(this.keys());
    }};

    CopyOnWriteArrayList<String> owners = new CopyOnWriteArrayList<>();

    @Override
    public Integer accountsCountByType(AccountType accountType) {
        return map.get(accountType);
    }

    @Override
    public List<String> activeAccountOwnersNames() {
        return owners ;
    }

    @Override
    public Integer totalActiveAccountsCount() {
        return owners.size();
    }

    @Override
    public void newAccountCreated(Account account) {
        owners.add(account.getFullName());
        System.out.println("map.get(account.getAccountType()) = " + map.get(account.getAccountType()));
        map.compute(account.getAccountType(),(k,v)->{
            if(v == null){
                v = 1;
                return v;
            }else return ++v;
        });
        System.out.println("map.get(account.getAccountType()) = " + map.get(account.getAccountType()));
    }
}
