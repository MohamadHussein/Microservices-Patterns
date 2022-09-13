package com.example.bank.account.aggregate;

import com.example.bank.account.AccountType;
import com.example.bank.account.common.command.*;
import com.example.bank.account.common.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.ApplyMore;
import org.axonframework.spring.stereotype.Aggregate;


import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    String id;
    String fullName;
    AccountType accountType;
    int balance;
    boolean active;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) { //constructor
        System.out.println("command = " + command);
        AccountCreatedEvent event = AccountCreatedEvent.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .accountId(command.getId())
                .accountType(command.getAccountType())
                .initialBalance(command.getInitialBalance())
                .build();

        System.out.println("event = " + event);
        ApplyMore apply = apply(event);

        System.out.println("apply = " + apply);
    }

    @CommandHandler
    public void depositAmount(DepositAmountCommand command) {
        System.out.println("command = " + command);
        if (this.active) {
            DepositAmountEvent event = DepositAmountEvent.builder()
                    .amount(command.getAmount())
                    .accountId(command.getId()).build();
            apply(event);
        }
        else throw new RuntimeException("account inactive !");
    }

    @EventSourcingHandler
    public void amountDeposited(DepositAmountEvent depositAmountEvent) {
        this.balance += depositAmountEvent.getAmount();
    }

    @CommandHandler
    public void activateAccount(ActivateAccountCommand command) {
        if (!this.active) {
            ActivateAccountEvent event = ActivateAccountEvent.builder()
                    .accountId(command.getId())
                    .build();
            apply(event);
        } else throw new RuntimeException("account already active");
    }

    @CommandHandler
    public void deactivateAccount(DeactivateAccountCommand command) {
        if (this.active) {
            DeactivateAccountEvent event = DeactivateAccountEvent.builder()
                    .accountId(command.getId())
                    .build();
            apply(event);
        } else throw new RuntimeException("account already inactive");
    }

    @CommandHandler
    public void withdrawAmount(WithdrawAmountCommand command) {
        if (this.active) {
            WithdrawAmountEvent event = WithdrawAmountEvent.builder()
                    .amount(command.getAmount())
                    .accountId(command.getId()).build();
            apply(event);
        }
        else throw new RuntimeException("account inactive !");
    }


    @EventSourcingHandler
    public void amountWithdrawn(WithdrawAmountEvent withdrawAmountEvent) {
        this.balance -= withdrawAmountEvent.getAmount();
    }

    @EventSourcingHandler
    public void accountCreated(AccountCreatedEvent accountCreatedEvent) {
        System.out.println("inside event");
        this.id = accountCreatedEvent.getAccountId();
        this.accountType = accountCreatedEvent.getAccountType();
        this.balance = accountCreatedEvent.getInitialBalance();
        this.fullName = accountCreatedEvent.getFirstName() + " " + accountCreatedEvent.getLastName();
        this.active = true;
        System.out.println("done with the event");
    }

    @EventSourcingHandler
    public void accountActivated(ActivateAccountEvent activateAccountEvent) {
        this.active = true;
    }

    @EventSourcingHandler
    public void accountDeactivated(DeactivateAccountEvent deactivateAccountEvent) {
        this.active = false;
    }
}
