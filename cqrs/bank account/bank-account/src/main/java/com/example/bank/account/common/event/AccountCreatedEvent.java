package com.example.bank.account.common.event;

import com.example.bank.account.AccountType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountCreatedEvent {
    String accountId;
    String firstName;
    String lastName;
    int initialBalance;
    AccountType accountType;
}
