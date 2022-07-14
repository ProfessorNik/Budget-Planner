package ru.shift.budgetplanner.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.exception.UserNotLoginException;

@Service
@AllArgsConstructor
public class GetCurrentUser {
    private final UserService service;

    public User exec(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotLoginException("authentication is null");
        }
        if(authentication instanceof AnonymousAuthenticationToken){
            throw new UserNotLoginException("user is anonymousUser");
        }

        String username = authentication.getName();
        return service.findUserByUsername(username);
    }
}
