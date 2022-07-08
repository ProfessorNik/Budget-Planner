package ru.shift.budgetplanner.service;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.JwtResponseDto;
import ru.shift.budgetplanner.dto.LoginDto;
import ru.shift.budgetplanner.exception.InvalidJwtToken;
import ru.shift.budgetplanner.exception.InvalidPasswordException;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthService(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponseDto auth(@NonNull LoginDto loginRequest){
        User user = userService.findUserByUsername(loginRequest.getUsername());
        if(user.getPassword().equals(loginRequest.getPassword())){
            throw new InvalidPasswordException("invalid password");
        }

        return generateJwtWithUserData(user);
    }

    public JwtResponseDto getAccessToken(@NonNull String refreshToken){
        if(!jwtProvider.validateRefreshToken(refreshToken)){
            throw new InvalidJwtToken("invalid refresh token");
        }
        User user = getUserByRefreshToken(refreshToken);
        return generateAccessTokenWithUserData(user);
    }

    public JwtResponseDto refresh(@NonNull String refreshToken){
        if(!jwtProvider.validateRefreshToken(refreshToken)){
            throw new InvalidJwtToken("invalid refresh token");
        }
        User user = getUserByRefreshToken(refreshToken);
        return generateJwtWithUserData(user);
    }

    private JwtResponseDto generateAccessTokenWithUserData(@NonNull User user){
        JwtResponseDto jwtResponse = new JwtResponseDto();
        jwtResponse.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return jwtResponse;
    }
    private JwtResponseDto generateJwtWithUserData(@NonNull User user){
        JwtResponseDto jwtResponse = new JwtResponseDto();
        jwtResponse.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtResponse.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return jwtResponse;
    }

    private User getUserByRefreshToken(@NonNull String refreshToken){
        Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        String usernameFromToken = claims.getSubject();
        return userService.findUserByUsername(usernameFromToken);
    }

}
