package ru.shift.budgetplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SpendingMoneyAndLimitDto {
    private BigDecimal spendingMoney;
    private BigDecimal spendingLimit;
}
