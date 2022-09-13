package com.example.procurementdepartment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PurchaseOrder {
    String id;
    String supplierId;
    Integer quantity;
    Double totalPrice;
}
