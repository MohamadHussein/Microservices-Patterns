package com.example.procurementdepartment.adapter.rest;

import com.example.procurementdepartment.model.PurchaseOrder;
import com.example.procurementdepartment.service.ProcurementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/procurement")
public class ProcurementController {

    private final ProcurementService procurementService;

    public ProcurementController(ProcurementService procurementService) {
        this.procurementService =procurementService;
    }

    @GetMapping("/{id}")
    public PurchaseOrder gettingById(@PathVariable String id){
        return procurementService.getById(id);
    }

    @GetMapping
    public List<PurchaseOrder> gettingAll(){
        return procurementService.getAll();
    }
    @GetMapping("/agg")
    public Map<String,Double> aggregatePO(){
        return procurementService.aggregatePurchasesCost();
    }
}
