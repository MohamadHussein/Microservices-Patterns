package com.example.salesdepartment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sale {
    String id;
    String customerId;
    Double totalPrice;
    Integer quantity;
}
