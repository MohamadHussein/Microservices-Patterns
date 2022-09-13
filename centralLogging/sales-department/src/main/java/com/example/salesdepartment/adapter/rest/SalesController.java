package com.example.salesdepartment.adapter.rest;

import com.example.salesdepartment.model.Sale;
import com.example.salesdepartment.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
@Slf4j
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }


    @GetMapping("/{id}")
    public Sale gettingById(@PathVariable String id){
        return salesService.getById(id);
    }

    @GetMapping
    public List<Sale> gettingAll(){
        return salesService.getAll();
    }
    @GetMapping("/agg")
    public Map<String,Double> aggregatePO(){
        log.info("Asked for sales aggregate");
        Map<String, Double> aggregateSalesAmount = salesService.aggregateSalesAmount();
        log.info("sales aggregated..");
        return aggregateSalesAmount;
    }
}
