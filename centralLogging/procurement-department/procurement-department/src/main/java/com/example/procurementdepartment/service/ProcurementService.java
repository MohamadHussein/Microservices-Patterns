package com.example.procurementdepartment.service;

import com.example.procurementdepartment.adapter.repository.ProcurementRepo;
import com.example.procurementdepartment.model.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProcurementService {
    private  final ProcurementRepo  procurementRepo;

    public ProcurementService(ProcurementRepo procurementRepo) {
        this.procurementRepo = procurementRepo;
    }

    public PurchaseOrder getById(String id){
        return procurementRepo.getById(id);
    }

    public Map<String,Double> aggregatePurchasesCost(){
        log.info("generating PO aggregate ...");
        return procurementRepo.getAll().stream().collect(() -> new HashMap<String, Double>(),
                (acc, po) -> acc.put(po.getId(), po.getTotalPrice()),
                (acc1, acc2) -> acc1.putAll(acc2));
    }

    public List<PurchaseOrder> getAll() {
        return procurementRepo.getAll();
    }
}
