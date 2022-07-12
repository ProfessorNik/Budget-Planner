package ru.shift.budgetplanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shift.budgetplanner.dto.RegistrationDto;
import ru.shift.budgetplanner.exception.RegistrationException;
import ru.shift.budgetplanner.service.RegistrationService;

import java.util.Optional;

@Controller
@RequestMapping("api/reg")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody RegistrationDto request) {
        try {
            service.registration(request);
        }catch (RegistrationException e){
            return ResponseEntity.of(Optional.of(e.getMessage()));
        }

        return ResponseEntity.ok("Registration successful!");
    }

}
