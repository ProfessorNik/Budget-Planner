package ru.shift.budgetplanner.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String password;
    private String repeatPassword;
}
