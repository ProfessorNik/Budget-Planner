package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.Purchase;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.AddPurchaseDto;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AddPurchase {
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;
    private final GetCurrentUser getCurrentUser;

    public void exec(@NonNull AddPurchaseDto addPurchaseRequest){
        User buyer = getCurrentUser.exec();
        String categoryName = addPurchaseRequest.getCategoryName();
        BigDecimal cost = addPurchaseRequest.getCost();

        Category category = categoryService.getCategoryByNameAndOwnerNotNull(categoryName, buyer);
        Purchase purchase = createPurchase(category, buyer, cost);
        purchaseService.savePurchase(purchase);
    }

    public Purchase createPurchase(Category category, User buyer, BigDecimal cost){
        Purchase newPurchase = new Purchase();
        newPurchase.setBuyer(buyer);
        newPurchase.setCategory(category);
        newPurchase.setCost(cost);
        newPurchase.setAddedTime(new Date().getTime());
        return newPurchase;
    }
}
