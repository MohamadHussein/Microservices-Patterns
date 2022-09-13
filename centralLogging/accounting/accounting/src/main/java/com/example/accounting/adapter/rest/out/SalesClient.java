package com.example.accounting.adapter.rest.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "sales-app")
public interface SalesClient {

    @GetMapping("/sales/agg")
    Map<String,Double> salesAggregate();
}
