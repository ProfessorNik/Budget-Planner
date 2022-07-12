package ru.shift.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shift.budgetplanner.domain.Debt;
import ru.shift.budgetplanner.domain.User;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findDebtsByDebtor(User user);
}
