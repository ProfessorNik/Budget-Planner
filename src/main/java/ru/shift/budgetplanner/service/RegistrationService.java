package ru.shift.budgetplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.RegistrationDto;
import ru.shift.budgetplanner.exception.RegistrationException;
import ru.shift.budgetplanner.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;

    public User registration(RegistrationDto registrationRequest){
        if(!registrationRequest.getPassword().equals(registrationRequest.getRepeatPassword())){
            throw new RegistrationException("password and repeat password aren't equals");
        }

        if(userRepository.findUserByUsername(registrationRequest.getUsername()) != null){
            throw new RegistrationException("user with this username already exist");
        }

        User newUser = new User();
        newUser.setUsername(registrationRequest.getUsername());
        newUser.setPassword(registrationRequest.getPassword());
        userRepository.save(newUser);
        return newUser;
    }
}
