package ru.shift.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.Purchase;
import ru.shift.budgetplanner.domain.User;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findPurchasesByBuyer(User buyer);
    List<Purchase> findPurchasesByBuyerAndCategory(User buyer, Category category);
}
