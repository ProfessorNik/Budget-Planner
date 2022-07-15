package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.Purchase;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.repository.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    void savePurchase(@NonNull Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public void deletePurchaseById(Long purchaseId) {
        purchaseRepository.deletePurchaseById(purchaseId);
    }

    public List<Purchase> getPurchasesByCategoryAndBuyer(Category category, User buyer){
        return purchaseRepository.findPurchasesByBuyerAndCategory(buyer, category);
    }
}
