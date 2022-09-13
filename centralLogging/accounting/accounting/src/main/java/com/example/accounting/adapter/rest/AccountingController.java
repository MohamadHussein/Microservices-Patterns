package com.example.accounting.adapter.rest;

import com.example.accounting.model.AccountingReport.AccountingReport;
import com.example.accounting.service.AccountingService.AccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounting")
@Slf4j
public class AccountingController {
    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @GetMapping("/report")
    public AccountingReport gettingReport(){
        log.info("asked accounting service for a report");
        log.info("report generation in progress");
        return accountingService.generateReport();
    }
}
