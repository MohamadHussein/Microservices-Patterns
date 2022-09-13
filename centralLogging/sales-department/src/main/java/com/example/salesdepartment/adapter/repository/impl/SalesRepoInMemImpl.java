package com.example.salesdepartment.adapter.repository.impl;

import com.example.salesdepartment.adapter.repository.SalesRepo;
import com.example.salesdepartment.model.Sale;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SalesRepoInMemImpl implements SalesRepo {
    Map<String, Sale> sales = new HashMap<String, Sale>(){{
        put("1",Sale.builder().id("1").quantity(50).customerId("1001").totalPrice(25000.0).build());
        put("2",Sale.builder().id("2").quantity(50).customerId("1002").totalPrice(1000.5).build());
        put("3",Sale.builder().id("3").quantity(50).customerId("1003").totalPrice(8400.0).build());
        put("4",Sale.builder().id("4").quantity(50).customerId("1004").totalPrice(7400.0).build());
        put("5",Sale.builder().id("5").quantity(50).customerId("1005").totalPrice(9600.0).build());
        put("6",Sale.builder().id("6").quantity(50).customerId("1006").totalPrice(3200.0).build());
    }};
    @Override
    public Sale save(Sale sale) {
        return sales.put(sale.getId(),sale);
    }

    @Override
    public Sale getById(String id) {
        return sales.get(id);
    }

    @Override
    public List<Sale> ordersByCustomerId(String customerId) {
        return sales.values()
                .stream()
                .filter(
                        sale -> sale.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sale> getAll() {
        return sales.values().stream().collect(Collectors.toList());
    }

}
