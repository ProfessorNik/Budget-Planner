package ru.shift.budgetplanner.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddPurchaseDto {
    private String categoryName;
    private BigDecimal cost;
    private List<String> debtorsName;
}
