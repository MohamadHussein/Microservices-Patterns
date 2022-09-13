package com.example.accounting.service.AccountingService;

import com.example.accounting.adapter.rest.out.SalesClient;
import com.example.accounting.model.AccountingReport.AccountingReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Service
public class AccountingService {
    private final static String PROC_URL= "http://proc-app/procurement/agg";

    private final RestTemplate restTemplate;
    private final SalesClient salesClient;

    public AccountingService(RestTemplate restTemplate, SalesClient salesClient) {
        this.restTemplate = restTemplate;
        this.salesClient = salesClient;
    }

    public AccountingReport generateReport() {
        return AccountingReport.builder().id(UUID.randomUUID().toString())
                .timeStamp(LocalDateTime.now())
                .totalCost(fetchTotalCost())
                .totalEarnings(fetchTotalEarnings())
                .build();

    }

    private Double fetchTotalEarnings() {
        return salesClient.salesAggregate()
                .values()
                .stream()
                .reduce(0.0, Double::sum);

    }

    private Double fetchTotalCost(){
        HashMap<String,Double> map = restTemplate.getForObject(PROC_URL, HashMap.class);
        System.out.println("map = " + map);
        return map
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
//                .stream().reduce(0.0,Double::sum);
    }
}
