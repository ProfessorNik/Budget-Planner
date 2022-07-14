package ru.shift.budgetplanner.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.exception.UserNotFoundException;
import ru.shift.budgetplanner.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User findUserByUsername(String username){
       User user = repository.findUserByUsername(username);
       if(user == null)
           throw new UserNotFoundException("user with this username not found");
       return user;
    }


}
