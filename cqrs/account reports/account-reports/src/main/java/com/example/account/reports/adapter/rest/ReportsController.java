package com.example.account.reports.adapter.rest;

import com.example.bank.account.common.query.OwnerNamesQuery;
import com.example.bank.account.common.query.TotalAccountCountByTypeQuery;
import com.example.bank.account.common.query.TotalAccountCountQuery;
import com.example.bank.account.model.AccountType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    private final QueryGateway queryGateway;

    public ReportsController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{accountType}/count")
    public Integer countByType(@PathVariable AccountType accountType) throws ExecutionException, InterruptedException {
        TotalAccountCountByTypeQuery query = new TotalAccountCountByTypeQuery(accountType);
        System.out.println("query = " + query);
        return queryGateway.query(query, Integer.class).get();
    }

    @GetMapping("/accounts/count")
    public Integer allAccountCount() throws ExecutionException, InterruptedException {
        TotalAccountCountQuery query = new TotalAccountCountQuery();
        return queryGateway.query(query, Integer.class).get();
    }
    @GetMapping("/owners")
    public List<String> allAccountOwners() throws ExecutionException, InterruptedException {
        OwnerNamesQuery q = new OwnerNamesQuery();
        return queryGateway.query(q, ResponseTypes.multipleInstancesOf(String.class)).get();
    }

}
