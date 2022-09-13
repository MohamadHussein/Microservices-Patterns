package com.example.bank.account.common.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class WithdrawAmountCommand {
    @TargetAggregateIdentifier
    String id;

    int amount;
}