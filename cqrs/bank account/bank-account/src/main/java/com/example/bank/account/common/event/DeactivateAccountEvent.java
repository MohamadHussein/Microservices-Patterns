package com.example.bank.account.common.event;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DeactivateAccountEvent {
    String accountId;
}
