package com.example.procurementdepartment.adapter.repository.impl;

import com.example.procurementdepartment.adapter.repository.ProcurementRepo;
import com.example.procurementdepartment.model.PurchaseOrder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Repository
public class ProcurementInMemImpl implements ProcurementRepo {
    Map<String, PurchaseOrder> orders = new HashMap<String, PurchaseOrder>(){{
        put("1",PurchaseOrder.builder().id("1").quantity(50).supplierId("12").totalPrice(1200.0).build());
        put("2",PurchaseOrder.builder().id("2").quantity(50).supplierId("12").totalPrice(100.0).build());
        put("3",PurchaseOrder.builder().id("3").quantity(50).supplierId("12").totalPrice(6200.0).build());
        put("4",PurchaseOrder.builder().id("4").quantity(50).supplierId("13").totalPrice(700.0).build());
        put("5",PurchaseOrder.builder().id("5").quantity(50).supplierId("14").totalPrice(8400.0).build());
        put("6",PurchaseOrder.builder().id("6").quantity(50).supplierId("15").totalPrice(100.0).build());
    }};
    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return orders.put(purchaseOrder.getId(),purchaseOrder);
    }

    @Override
    public PurchaseOrder getById(String id) {
        return orders.get(id);
    }

    @Override
    public List<PurchaseOrder> ordersBySupplierId(String supplierId) {
        return orders.values().stream()
                .filter(
                        purchaseOrder -> purchaseOrder.getSupplierId().equals(supplierId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return orders.values().stream().collect(Collectors.toList());
    }
}
