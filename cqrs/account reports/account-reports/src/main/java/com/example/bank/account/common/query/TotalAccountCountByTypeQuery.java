package com.example.bank.account.common.query;

import com.example.bank.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalAccountCountByTypeQuery {
    AccountType accountType;
}
