package ru.shift.budgetplanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shift.budgetplanner.dto.JwtResponseDto;
import ru.shift.budgetplanner.dto.LoginDto;
import ru.shift.budgetplanner.dto.RefreshJwtDto;
import ru.shift.budgetplanner.service.AuthService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginRequest){
        var jwtResponse = authService.auth(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("access")
    public ResponseEntity<JwtResponseDto> getAccessToken(@RequestBody RefreshJwtDto accessRequest){
        var jwtResponse = authService.getAccessToken(accessRequest.getRefreshToken());
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody RefreshJwtDto refreshRequest){
        var jwtResponse = authService.refresh(refreshRequest.getRefreshToken());
        return ResponseEntity.ok(jwtResponse);
    }
}
