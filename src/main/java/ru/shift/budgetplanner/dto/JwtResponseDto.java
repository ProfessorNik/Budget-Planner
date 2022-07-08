package ru.shift.budgetplanner.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
}
