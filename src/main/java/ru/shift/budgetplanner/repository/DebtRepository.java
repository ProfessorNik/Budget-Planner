package ru.shift.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shift.budgetplanner.domain.Debt;
import ru.shift.budgetplanner.domain.User;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findDebtsByDebtor(User user);
}
