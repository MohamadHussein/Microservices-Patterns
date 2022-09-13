package com.example.procurementdepartment.adapter.repository;

import com.example.procurementdepartment.model.PurchaseOrder;

import java.util.List;

public interface ProcurementRepo {
    PurchaseOrder save(PurchaseOrder purchaseOrder);
    PurchaseOrder getById(String id);
    List<PurchaseOrder> ordersBySupplierId(String supplierId);
    List<PurchaseOrder> getAll();
}
