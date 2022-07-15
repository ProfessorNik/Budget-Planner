package ru.shift.budgetplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeSpendingLimitDto {
    String categoryName;
    BigDecimal spendingLimit;
}
