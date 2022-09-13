package com.example.bank.account.common.command;

import com.example.bank.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountCommand {
    @TargetAggregateIdentifier
    private String id;

    private String firstName;
    private String lastName;
    private int initialBalance;
    private AccountType accountType;
}
