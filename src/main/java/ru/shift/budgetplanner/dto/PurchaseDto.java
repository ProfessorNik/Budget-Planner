package ru.shift.budgetplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private BigDecimal cost;
    private String categoryName;
}
