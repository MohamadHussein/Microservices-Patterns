package com.example.accounting.model.AccountingReport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class AccountingReport {
    String id;
    LocalDateTime timeStamp;
    Double totalEarnings;
    Double totalCost;

}
