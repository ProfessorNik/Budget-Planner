package ru.shift.budgetplanner.service;

import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.exception.UserNotFoundException;
import ru.shift.budgetplanner.repository.UserRepository;

@Service
public class UserService {
    private UserRepository repository;

    User findUserByUsername(String username){
       User user = repository.findUserByUsername(username);
       if(user == null)
           throw new UserNotFoundException("user with this username not found");
       return user;
    }
}