package com.example.account.reports.model;

import com.example.bank.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class Account {

    String id;

    String fullName;

    AccountType accountType;
    int balance;

}
