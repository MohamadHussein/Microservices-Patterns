package com.example.account.reports.eventhandlers;

import com.example.account.reports.adapter.repository.AccountReportsRepo;
import com.example.account.reports.model.Account;
import com.example.bank.account.common.event.AccountCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountEventsHandler {
    private final AccountReportsRepo accountReportsRepo;

    public AccountEventsHandler(AccountReportsRepo accountReportsRepo) {
        this.accountReportsRepo = accountReportsRepo;
    }

    @EventHandler
    public void AccountCreatedEventHandler(AccountCreatedEvent accountCreatedEvent){
        System.out.println("accountCreatedEvent "  + accountCreatedEvent);
        Account account = Account.builder()
                .accountType(accountCreatedEvent.getAccountType())
                .balance(accountCreatedEvent.getInitialBalance())
                .id(accountCreatedEvent.getAccountId())
                .fullName(accountCreatedEvent.getFirstName() + " " + accountCreatedEvent.getLastName()).build();
        accountReportsRepo.newAccountCreated(account);

    }
}
