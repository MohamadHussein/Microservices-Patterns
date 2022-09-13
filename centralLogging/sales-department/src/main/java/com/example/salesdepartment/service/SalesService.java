package com.example.salesdepartment.service;

import com.example.salesdepartment.adapter.repository.SalesRepo;
import com.example.salesdepartment.model.Sale;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesService {
    private final SalesRepo salesRepo;

    public SalesService(SalesRepo salesRepo) {
        this.salesRepo = salesRepo;
    }

    public Sale getById(String id){
        return salesRepo.getById(id);
    }

    public List<Sale> getAll() {
        return salesRepo.getAll();
    }

    public Map<String, Double> aggregateSalesAmount() {
        return salesRepo.getAll().stream().collect(() -> new HashMap<String, Double>(),
                (acc, po) -> acc.put(po.getId(), po.getTotalPrice()),
                (acc1, acc2) -> acc1.putAll(acc2));
    }
}
