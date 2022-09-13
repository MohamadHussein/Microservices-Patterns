package com.example.bank.account.common.event;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ActivateAccountEvent {
    String accountId;
}
