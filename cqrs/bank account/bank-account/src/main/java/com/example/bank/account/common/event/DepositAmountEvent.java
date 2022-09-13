package com.example.bank.account.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class DepositAmountEvent {
    String accountId;
    int amount;
}
