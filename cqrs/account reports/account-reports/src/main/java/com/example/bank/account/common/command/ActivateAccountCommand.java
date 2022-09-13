package com.example.bank.account.common.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Data
public class ActivateAccountCommand {
    @TargetAggregateIdentifier
    String id;
}
