package com.example.salesdepartment.adapter.repository;

import com.example.salesdepartment.model.Sale;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepo {
    Sale save(Sale sale);
    Sale getById(String id);
    List<Sale> ordersByCustomerId(String customerId);
    List<Sale> getAll();
}
