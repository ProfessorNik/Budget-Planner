package ru.shift.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shift.budgetplanner.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
